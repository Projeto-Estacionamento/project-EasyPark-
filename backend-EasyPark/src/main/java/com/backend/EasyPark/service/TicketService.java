package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.entities.Ticket;
import com.backend.EasyPark.repository.TicketRepository;
// import com.backend.EasyPark.service.ConfiguracaoSistemaService;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ConfiguracaoSistemaService configuracaoSistemaService;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ConfiguracaoSistemaService configuracaoSistemaService) {
        this.ticketRepository = ticketRepository;
        this.configuracaoSistemaService = configuracaoSistemaService;
    }

    public TicketDTO criarTicket(TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        ticket.setHoraChegada(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDTO(savedTicket);
    }

    public TicketDTO buscarTicketPorId(Long id) {
        return ticketRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
    }

    public List<TicketDTO> listarTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO finalizarTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticket.setHoraSaida(LocalDateTime.now());
        BigDecimal valorTotal = calcularValorTicket(ticket);
        ticket.setValorTotalPagar(valorTotal.doubleValue());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToDTO(updatedTicket);
    }

    public List<TicketDTO> buscarTicketsPorUsuario(Long usuarioId) {
        return ticketRepository.findByUsuarioId(usuarioId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setPlacaVeiculo(ticketDTO.getPlacaVeiculo());
        ticket.setHoraChegada(ticketDTO.getHoraChegada());
        ticket.setHoraSaida(ticketDTO.getHoraSaida());
        return ticket;
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getPlacaVeiculo(),
                ticket.getHoraChegada(),
                ticket.getHoraSaida(),
                ticket.getTotalHoras(),
                ticket.getValorTotalPagar()
        );
    }

    public BigDecimal calcularValorTicket(Ticket ticket) {
        BigDecimal valorPorHora = configuracaoSistemaService.getValorPorHora();
        long horasEstacionado = ChronoUnit.HOURS.between(ticket.getHoraChegada(), ticket.getHoraSaida());
        return valorPorHora.multiply(BigDecimal.valueOf(horasEstacionado));
    }
}
