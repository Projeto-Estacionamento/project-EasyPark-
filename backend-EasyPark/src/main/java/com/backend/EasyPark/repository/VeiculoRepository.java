package com.backend.EasyPark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByOcupandoVagaTrue();
    
    // Método adicional que pode ser útil
    List<Veiculo> findByOcupandoVagaFalse();
    
    List<Veiculo> findByTipoVeiculo(String tipoVeiculo);
}
