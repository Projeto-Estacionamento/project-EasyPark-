package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.repository.VeiculoRepository;
import com.backend.EasyPark.util.validacao.ValidacaoVeiculo;
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
    private final TicketMapper ticketMapper;
    private final VeiculoRepository veiculoRepository;
    private ValidacaoVeiculo validarVeiculo = new ValidacaoVeiculo();

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ConfiguracaoSistemaService configuracaoSistemaService,
                         TicketMapper ticketMapper,
                         VeiculoRepository veiculoRepository) {
        this.ticketRepository = ticketRepository;
        this.configuracaoSistemaService = configuracaoSistemaService;
        this.ticketMapper = ticketMapper;
        this.veiculoRepository = veiculoRepository;
    }



    public TicketDTO criarTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setHoraChegada(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(savedTicket);
    }

    public TicketDTO buscarTicketPorId(Long id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
    }

    public List<TicketDTO> listarTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO finalizarTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticket.setHoraSaida(LocalDateTime.now());
        BigDecimal valorTotal = calcularValorTicket(ticket);
        ticket.setValorTotalPagar(valorTotal.doubleValue());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(updatedTicket);
    }

    public List<TicketDTO> buscarTicketsPorUsuario(Long usuarioId) {
        return ticketRepository.findByUsuarioId(usuarioId).stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BigDecimal calcularValorTicket(Ticket ticket) {
        BigDecimal valorPorHora = configuracaoSistemaService.getValorPorHora();
        long horasEstacionado = ChronoUnit.HOURS.between(ticket.getHoraChegada(), ticket.getHoraSaida());
        return valorPorHora.multiply(BigDecimal.valueOf(horasEstacionado));
    }

    public Optional<Ticket> registrarEntrada (Veiculo placa) {
        validarVeiculo.verificarSeVeiculoEstaCadastrado(placa.getPlaca());


        return null;
    }
}
