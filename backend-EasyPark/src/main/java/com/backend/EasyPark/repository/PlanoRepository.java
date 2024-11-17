package com.backend.EasyPark.repository;

import com.backend.EasyPark.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {

}
