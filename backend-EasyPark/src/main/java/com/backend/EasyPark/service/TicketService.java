package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.enums.TipoTicket;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.VeiculoRepository;
import com.backend.EasyPark.util.VeiculoMapper;
import com.backend.EasyPark.util.validacao.ValidarVeiculo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.entities.Ticket;
import com.backend.EasyPark.repository.TicketRepository;
import com.backend.EasyPark.util.TicketMapper;
// import com.backend.EasyPark.service.ConfiguracaoSistemaService;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ConfiguracaoSistemaService configuracaoSistemaService;
    private final ValidarVeiculo validarVeiculo;
    private final VeiculoRepository veiculoRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ConfiguracaoSistemaService configuracaoSistemaService,
                         ValidarVeiculo validarVeiculo,
                         VeiculoRepository veiculoRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.configuracaoSistemaService = configuracaoSistemaService;
        this.validarVeiculo = validarVeiculo;
        this.veiculoRepository = veiculoRepository;
        this.ticketMapper = ticketMapper;

    }

    public TicketDTO criarTicket(TicketDTO ticket) throws EstacionamentoException {
        if (ticket == null || ticket.getPlacaVeiculo() == null) {
            throw new EstacionamentoException("Ticket ou placa do veículo não podem ser nulos");
        }

        Ticket ticketEntity = new Ticket();
        ticketEntity.setPlacaVeiculo(ticket.getPlacaVeiculo());
        ticketEntity.setHoraChegada(LocalDateTime.now());

        try {
            // Verifica se o veículo existe e é mensalista
            boolean isMensalista = validarVeiculo.isVeiculoMensalista(ticket.getPlacaVeiculo());

            // Define o tipo do ticket baseado na verificação
            ticketEntity.setTipoTicket(isMensalista ? TipoTicket.TICKET_MENSALISTA : TipoTicket.TICKET_AVULSO);

            // Se for mensalista, atualiza o veículo
            if (isMensalista) {
                VeiculoDTO veiculo = validarVeiculo.buscarVeiculoPorPlaca(ticket.getPlacaVeiculo());
                Veiculo veiculoEntity = VeiculoMapper.toEntity(veiculo);
                veiculoEntity.setOcupandoVaga(true);
                veiculoRepository.save(veiculoEntity);
            }

            // Salva o ticket
            return ticketMapper.toDTO(ticketRepository.save(ticketEntity));

        } catch (EntityNotFoundException e) {
            // Se o veículo não for encontrado, cria um ticket avulso
            ticketEntity.setTipoTicket(TipoTicket.TICKET_AVULSO);
            return ticketMapper.toDTO(ticketRepository.save(ticketEntity));
        } catch (Exception e) {
            throw new EstacionamentoException("Erro ao criar o ticket: " + e.getMessage());
        }
    }


    public TicketDTO buscarTicketPorId(Integer id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
    }

    public List<TicketDTO> listarTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO finalizarTicket(Integer id) {
        // Finaliza um ticket, calculando o tempo total e o valor a pagar
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket não encontrado"));
        ticket.setHoraSaida(LocalDateTime.now());
        ticket.setTotalHoras(Duration.between(ticket.getHoraChegada(), ticket.getHoraSaida()));

        BigDecimal valorTotal = calcularValorTicket(ticketMapper.toDTO(ticket));
        ticket.setValorTotalPagar(valorTotal.doubleValue());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(updatedTicket);
    }

    public BigDecimal calcularValorTicket(TicketDTO ticket) {
        // Verifica se o ticket é de um cliente mensalista ou se a permanência foi inferior a 15 minutos
        boolean isMenosDe15Minutos = ticket.getTotalHoras().toMinutes() < 15;

        if (isMenosDe15Minutos || ticket.getTipoTicket().equals(TipoTicket.TICKET_MENSALISTA)) {
            return BigDecimal.ZERO; // Isenção de pagamento
        }

        BigDecimal valorPorHora = configuracaoSistemaService.getValorPorHora();
        long horasEstacionado = ticket.getTotalHoras().toHours();

        // Arredonda para cima qualquer fração de hora
        if (ticket.getTotalHoras().toMinutesPart() > 0) {
            horasEstacionado++;
        }

        return valorPorHora.multiply(BigDecimal.valueOf(horasEstacionado));
    }


}
