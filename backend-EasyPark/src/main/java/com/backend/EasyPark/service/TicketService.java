package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ConfiguracaoSistemaService configuracaoSistemaService,
                         TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.configuracaoSistemaService = configuracaoSistemaService;
        this.ticketMapper = ticketMapper;
    }

    public TicketDTO criarTicket(TicketDTO ticketDTO) {
        // Cria um novo ticket e salva no banco de dados
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setHoraChegada(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(savedTicket);
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
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticket.setHoraSaida(LocalDateTime.now());
        ticket.setTotalHoras(Duration.between(ticket.getHoraChegada(), ticket.getHoraSaida()));
        BigDecimal valorTotal = calcularValorTicket(ticket);
        ticket.setValorTotalPagar(valorTotal.doubleValue());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(updatedTicket);
    }

    public BigDecimal calcularValorTicket(Ticket ticket) {
        BigDecimal valorPorHora = configuracaoSistemaService.getValorPorHora();
        long horasEstacionado = ticket.getTotalHoras().toHours();
        return valorPorHora.multiply(BigDecimal.valueOf(horasEstacionado));
    }
}
