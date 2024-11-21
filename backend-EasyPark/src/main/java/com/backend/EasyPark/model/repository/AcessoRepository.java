package com.backend.EasyPark.model.repository;

import com.backend.EasyPark.model.entities.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer>, JpaSpecificationExecutor<Acesso> {
    Acesso findByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Acesso> findByEmail(String email);

}
