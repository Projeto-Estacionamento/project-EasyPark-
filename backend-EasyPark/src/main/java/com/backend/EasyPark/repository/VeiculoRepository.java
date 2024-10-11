package com.backend.EasyPark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {
    
}
