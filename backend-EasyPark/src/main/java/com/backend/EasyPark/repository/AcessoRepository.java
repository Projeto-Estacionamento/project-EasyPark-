package com.backend.EasyPark.repository;

import com.backend.EasyPark.entities.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    Acesso findByUsername(String username);
}
