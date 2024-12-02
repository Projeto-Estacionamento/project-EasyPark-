package com.backend.EasyPark.model.repository;

import java.util.List;
import java.util.Optional;

import com.backend.EasyPark.model.enums.TipoTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.model.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findByPlacaVeiculo(String placaVeiculo);

    List<Ticket> findByPlacaVeiculo(Ticket placaVeiculo);

    Optional<Ticket> findByPlacaVeiculoAndHoraSaidaIsNull(String placaVeiculo);

    List<Ticket> findByTipoTicket(TipoTicket tipoTicket);
}