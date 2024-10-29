package com.backend.EasyPark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    
    Fabricante findByMarca(String marca);
    
    List<Fabricante> findByModeloContainingIgnoreCase(String modelo);
    
    List<Fabricante> findByMarcaContainingIgnoreCase(String marca);
    
    List<Fabricante> findByAno(Integer ano);
    
    List<Fabricante> findByMarcaAndModelo(String marca, String modelo);
    
    boolean existsByMarcaAndModeloAndAno(String marca, String modelo, Integer ano);
}
