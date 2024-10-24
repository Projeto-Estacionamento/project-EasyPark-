package com.backend.EasyPark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> criarTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO novoTicket = ticketService.criarTicket(ticketDTO);
        return new ResponseEntity<>(novoTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> buscarTicketPorId(@PathVariable Long id) {
        TicketDTO ticket = ticketService.buscarTicketPorId(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> listarTickets() {
        List<TicketDTO> tickets = ticketService.listarTickets();
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<TicketDTO> finalizarTicket(@PathVariable Long id) {
        TicketDTO ticketFinalizado = ticketService.finalizarTicket(id);
        return ResponseEntity.ok(ticketFinalizado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketDTO>> buscarTicketsPorUsuario(@PathVariable Long usuarioId) {
        List<TicketDTO> tickets = ticketService.buscarTicketsPorUsuario(usuarioId);
        return ResponseEntity.ok(tickets);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
