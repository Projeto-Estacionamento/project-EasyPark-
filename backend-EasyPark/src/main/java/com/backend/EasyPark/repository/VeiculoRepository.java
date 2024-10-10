package com.backend.EasyPark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.EasyPark.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {
    
}
