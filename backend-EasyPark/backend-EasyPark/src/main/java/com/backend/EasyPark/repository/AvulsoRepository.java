/*
package com.backend.EasyPark.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Avulso;

@Repository
public interface AvulsoRepository extends JpaRepository<Avulso, Long> {
    
    Optional<Avulso> findByPlacaVeiculo(String placaVeiculo);
    
    List<Avulso> findByHoraChegadaBetween(LocalDateTime inicio, LocalDateTime fim);
    
    List<Avulso> findByHoraSaidaIsNull();
    
    List<Avulso> findByValorTotal(boolean valorTotal);
    
    Optional<Avulso> findTopByPlacaVeiculoOrderByHoraChegadaDesc(String placaVeiculo);
}
*/
