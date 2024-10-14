package com.backend.EasyPark.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    // Busca um ticket pelo número
    // Optional<Ticket> findByNumero(String numero);
    
    
    List<Ticket> findByHoraChegadaBetween(LocalDateTime inicio, LocalDateTime fim);
}
