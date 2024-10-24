package com.backend.EasyPark.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.EasyPark.enums.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    Optional<Ticket> findByNumero(String numero);
    
    List<Ticket> findByUsuarioId(Long usuarioId);
    
    List<Ticket> findByDataEntradaBetween(LocalDateTime inicio, LocalDateTime fim);
    
    List<Ticket> findByStatusPagamento(boolean statusPagamento);

    int countByTipoVeiculo(TipoVeiculo tipoVeiculo);
}
