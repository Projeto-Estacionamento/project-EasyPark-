package com.backend.EasyPark.repository;

import com.backend.EasyPark.entities.AssinaturaPlano;
import com.backend.EasyPark.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssinaturaPlanoRepository extends JpaRepository<AssinaturaPlano, Integer> {
    List<AssinaturaPlano> findByUsuarioIdAndAtivoTrue(Integer id);
}
