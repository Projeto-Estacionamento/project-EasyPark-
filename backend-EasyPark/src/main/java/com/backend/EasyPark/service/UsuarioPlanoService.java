/*
package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.UsuarioPlanoDTO;
import com.backend.EasyPark.entities.UsuarioPlano;
import com.backend.EasyPark.repository.UsuarioPlanoRepository;
import com.backend.EasyPark.util.UsuarioPlanoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioPlanoService {

    private final UsuarioPlanoRepository usuarioPlanoRepository;
    private final UsuarioPlanoMapper usuarioPlanoMapper;

    public UsuarioPlanoDTO criarUsuarioPlano(UsuarioPlanoDTO usuarioPlanoDTO) {
        UsuarioPlano usuarioPlano = usuarioPlanoMapper.toEntity(usuarioPlanoDTO);
        usuarioPlano = usuarioPlanoRepository.save(usuarioPlano);
        return usuarioPlanoMapper.toDTO(usuarioPlano);
    }

    public UsuarioPlanoDTO atualizarUsuarioPlano(Integer id, UsuarioPlanoDTO usuarioPlanoDTO) {
        UsuarioPlano usuarioPlano = usuarioPlanoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano do usuário não encontrado"));
        usuarioPlano.setStatus(usuarioPlanoDTO.isStatus());
        usuarioPlano.setDataPagamento(usuarioPlanoDTO.getDataPagamento());
        usuarioPlano.setDataVencimento(usuarioPlanoDTO.getDataVencimento());
        usuarioPlano = usuarioPlanoRepository.save(usuarioPlano);
        return usuarioPlanoMapper.toDTO(usuarioPlano);
    }

    public void excluirUsuarioPlano(Integer id) {
        UsuarioPlano usuarioPlano = usuarioPlanoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano do usuário não encontrado"));
        usuarioPlanoRepository.delete(usuarioPlano);
    }
}

*/
