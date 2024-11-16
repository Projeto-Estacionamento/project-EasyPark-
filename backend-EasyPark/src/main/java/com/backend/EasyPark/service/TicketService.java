package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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


    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ConfiguracaoSistemaService configuracaoSistemaService,
                         ValidarVeiculo validarVeiculo,
                         VeiculoRepository veiculoRepository) {
        this.ticketRepository = ticketRepository;
        this.configuracaoSistemaService = configuracaoSistemaService;
        this.validarVeiculo = validarVeiculo;
        this.veiculoRepository = veiculoRepository;
    }
    public TicketDTO criarTicket(TicketDTO ticket) throws EstacionamentoException {
        if (ticket == null || ticket.getPlacaVeiculo() == null) {
            throw new EstacionamentoException("Ticket ou placa do veículo não podem ser nulos");
        }
        Optional<Veiculo> veiculoOpt = veiculoRepository.buscarVeiculoComAssinaturaValida(ticket.getPlacaVeiculo());

        if (veiculoOpt.isPresent()) {

        }

        Ticket ticketEntity = new Ticket();
        ticketEntity.setPlacaVeiculo(ticket.getPlacaVeiculo());
        ticketEntity.setHoraChegada(LocalDateTime.now());
        try {

                VeiculoDTO veiculo = validarVeiculo.buscarVeiculoPorPlaca(ticket.getPlacaVeiculo());
                Veiculo veiculoEntity = VeiculoMapper.toEntity(veiculo);
                veiculoEntity.setOcupandoVaga(true);
                veiculoRepository.save(veiculoEntity);

            // Salva o ticket
            return TicketMapper.toDTO(ticketRepository.save(ticketEntity));

        } catch (EntityNotFoundException e) {
            // Se o veículo não for encontrado, cria um ticket avulso
            ticketEntity.setTipoTicket(TipoTicket.TICKET_AVULSO);
            return TicketMapper.toDTO(ticketRepository.save(ticketEntity));
        } catch (Exception e) {
            throw new EstacionamentoException("Erro ao criar o ticket: " + e.getMessage());
        }
    }


    public TicketDTO buscarTicketPorId(Integer id) {
        return ticketRepository.findById(id)
                .map(TicketMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
    }

    public List<TicketDTO> listarTickets() {
        return ticketRepository.findAll().stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO finalizarTicket(Integer id) {
        // Finaliza um ticket, calculando o tempo total e o valor a pagar
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket não encontrado"));
        ticket.setHoraSaida(LocalDateTime.now());
        ticket.setTotalHoras(Duration.between(ticket.getHoraChegada(), ticket.getHoraSaida()));

        BigDecimal valorTotal = calcularValorTicket(TicketMapper.toDTO(ticket));
        ticket.setValorTotalPagar(valorTotal.doubleValue());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return TicketMapper.toDTO(updatedTicket);
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


