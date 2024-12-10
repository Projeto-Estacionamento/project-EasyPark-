package com.backend.EasyPark.model.repository;

import com.backend.EasyPark.model.entities.Plano;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {
    List<Plano>findByTipoVeiculo(TipoVeiculo tipoVeiculo);
}
