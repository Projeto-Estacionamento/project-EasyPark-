package com.backend.EasyPark.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.enums.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    Optional<Ticket> findByPlacaVeiculo(String placaVeiculo);
    
    List<Ticket> findByPlacaVeiculo(Ticket placaVeiculo);
    
    //List<Ticket> findByDataEntradaBetween(LocalDateTime inicio, LocalDateTime fim);
    
    //List<Ticket> findByStatusPagamento(boolean statusPagamento);

   // String countByTipoVeiculo(TipoVeiculo tipoVeiculo);

    Optional<Ticket> findByPlacaVeiculoAndHoraSaidaIsNull(String placaVeiculo);
}
