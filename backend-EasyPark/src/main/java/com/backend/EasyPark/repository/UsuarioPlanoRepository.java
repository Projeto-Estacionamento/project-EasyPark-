package com.backend.EasyPark.repository;

import com.backend.EasyPark.entities.UsuarioPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPlanoRepository extends JpaRepository<UsuarioPlano, Integer> {
}
