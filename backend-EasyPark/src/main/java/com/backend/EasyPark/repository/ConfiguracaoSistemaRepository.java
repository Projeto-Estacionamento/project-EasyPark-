package com.backend.EasyPark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.ConfiguracaoSistema;

@Repository
public interface ConfiguracaoSistemaRepository extends JpaRepository<ConfiguracaoSistema, Long> {
    
    Optional<ConfiguracaoSistema> findTopByOrderByIdAsc();
    
    Optional<ConfiguracaoSistema> findByQtdMotoAndQtdCarro(int qtdMoto, int qtdCarro);
    
    Optional<ConfiguracaoSistema> findByValorHoraMotoAndValorHoraCarro(double valorHoraMoto, double valorHoraCarro);
    
    Optional<ConfiguracaoSistema> findByHoraMaximaAvulso(double horaMaximaAvulso);
    
    Optional<ConfiguracaoSistema> findTopByOrderByIdDesc();
}
