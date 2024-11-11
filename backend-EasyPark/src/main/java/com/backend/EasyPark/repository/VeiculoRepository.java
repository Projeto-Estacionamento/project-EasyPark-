package com.backend.EasyPark.repository;

import java.util.List;
import java.util.Optional;

import com.backend.EasyPark.dto.VeiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    List<Veiculo> findByOcupandoVagaTrue();
    // Método adicional que pode ser útil
    List<Veiculo> findByOcupandoVagaFalse();
    
    List<Veiculo> findByTipoVeiculo(String tipoVeiculo);

    Veiculo findByPlaca(String placaVeiculo);
}
