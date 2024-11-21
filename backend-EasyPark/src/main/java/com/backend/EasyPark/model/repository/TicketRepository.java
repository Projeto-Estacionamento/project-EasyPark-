package com.backend.EasyPark.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.model.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    Optional<Ticket> findByPlacaVeiculo(String placaVeiculo);
    
    List<Ticket> findByPlacaVeiculo(Ticket placaVeiculo);
    
    //List<Ticket> findByDataEntradaBetween(LocalDateTime inicio, LocalDateTime fim);
    
    //List<Ticket> findByStatusPagamento(boolean statusPagamento);

   // String countByTipoVeiculo(TipoVeiculo tipoVeiculo);

    Optional<Ticket> findByPlacaVeiculoAndHoraSaidaIsNull(String placaVeiculo);
}
