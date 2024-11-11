package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.EasyPark.util.validacao.ValidacaoUsuario;
import com.backend.EasyPark.util.validacao.ValidacaoVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.repository.UsuarioRepository;
import com.backend.EasyPark.util.UsuarioMapper;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    private ValidacaoUsuario validacaoUsuario;

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
       validacaoUsuario.validarUsuario(usuarioDTO);
        if (usuarioDTO.getVeiculosDTO() != null) {
            veiculoService.validarVeiculo(usuarioDTO.getVeiculosDTO());
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUsuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        validacaoUsuario.validarUsuario(usuarioDTO);
        if (usuarioDTO.getVeiculosDTO() != null) {
            veiculoService.validarVeiculo(usuarioDTO.getVeiculosDTO());
        }
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioMapper.updateUsuarioFromDTO(usuario, usuarioDTO);
                    return usuarioMapper.toDTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf).stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

}