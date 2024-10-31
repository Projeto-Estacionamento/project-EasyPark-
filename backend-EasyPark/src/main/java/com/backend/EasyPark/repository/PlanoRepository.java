package com.backend.EasyPark.repository;


import com.backend.EasyPark.entities.UsuarioPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<UsuarioPlano, Integer> {

}
