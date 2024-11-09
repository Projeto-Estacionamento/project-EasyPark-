package com.backend.EasyPark.controller;

import java.util.List;

import com.backend.EasyPark.exception.EstacionamentoException;
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
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> criarTicket(@RequestBody TicketDTO ticketDTO) throws EstacionamentoException {
        // Cria um novo ticket
        TicketDTO novoTicket = ticketService.criarTicket(ticketDTO);
        return new ResponseEntity<>(novoTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> buscarTicketPorId(@PathVariable Integer id) {
        // Busca um ticket pelo ID
        TicketDTO ticket = ticketService.buscarTicketPorId(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> listarTickets() {
        List<TicketDTO> tickets = ticketService.listarTickets();
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<TicketDTO> finalizarTicket(@PathVariable Integer id) {
        // Finaliza um ticket (registra a saída e calcula o valor)
        TicketDTO ticketFinalizado = ticketService.finalizarTicket(id);
        return ResponseEntity.ok(ticketFinalizado);
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
