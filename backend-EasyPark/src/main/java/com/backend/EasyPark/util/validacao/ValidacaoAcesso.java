package com.backend.EasyPark.util.validacao;

import com.backend.EasyPark.dto.AcessoDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Component
public class ValidacaoAcesso {

    public void validarAcesso(AcessoDTO acessoDTO) {
        if (acessoDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados de acesso não podem ser nulos");
        }

        if (!StringUtils.hasText(acessoDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome de usuário é obrigatório");
        }

        if (!StringUtils.hasText(acessoDTO.getSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha é obrigatória");
        }

    }
}
