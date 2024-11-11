package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.entities.Acesso;
import com.backend.EasyPark.util.AcessoMapper;
import com.backend.EasyPark.util.validacao.ValidacaoAcesso;
import com.backend.EasyPark.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private AcessoMapper acessoMapper;

    @Autowired
    private ValidacaoAcesso validacaoAcesso;

    public AcessoDTO login(AcessoDTO acessoDTO) {
        validacaoAcesso.validarAcesso(acessoDTO);
        
        Acesso acesso = acessoRepository.findByUsername(acessoDTO.getUsername());
        if (acesso == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        if (!acesso.getSenha().equals(acessoDTO.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        return acessoMapper.toDTO(acesso);
    }
} 