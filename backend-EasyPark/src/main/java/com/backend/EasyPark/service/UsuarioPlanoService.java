package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.UsuarioPlanoDTO;
import com.backend.EasyPark.entities.UsuarioPlano;
import com.backend.EasyPark.repository.UsuarioPlanoRepository;
import com.backend.EasyPark.util.UsuarioPlanoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioPlanoService {

    @Autowired
    private UsuarioPlanoRepository usuarioPlanoRepository;

    @Autowired
    private UsuarioPlanoMapper usuarioPlanoMapper;

    public UsuarioPlanoDTO criarUsuarioPlano(UsuarioPlanoDTO usuarioPlanoDTO) {
        UsuarioPlano usuarioPlano = usuarioPlanoMapper.toEntity(usuarioPlanoDTO);
        usuarioPlano = usuarioPlanoRepository.save(usuarioPlano);
        return usuarioPlanoMapper.toDTO(usuarioPlano);
    }

    public List<UsuarioPlanoDTO> listarUsuarioPlanos() {
        return usuarioPlanoRepository.findAll().stream()
                .map(usuarioPlanoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioPlanoDTO buscarUsuarioPlanoPorId(Integer id) {
        return usuarioPlanoRepository.findById(id)
                .map(usuarioPlanoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário Plano não encontrado"));
    }


}
