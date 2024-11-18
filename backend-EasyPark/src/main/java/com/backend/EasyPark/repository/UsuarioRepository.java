package com.backend.EasyPark.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.EasyPark.seletor.UsuarioSeletor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.backend.EasyPark.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

    Usuario findByCpf(String cpf);
    
    List<Usuario> findByEmail(String email);

    Usuario findByTelefone(String telefone);


}
