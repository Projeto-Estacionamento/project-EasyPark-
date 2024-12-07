package com.backend.EasyPark.util.validacao;

import com.backend.EasyPark.dto.AcessoDTO;

public class ValidacaoAcesso {

    public void validarAcesso(AcessoDTO acessoDTO) {
        if (acessoDTO.getUsername() == null || acessoDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username não pode ser vazio");
        }
        if (!acessoDTO.getUsername().matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Username não pode conter caracteres especiais");
        }
        if (acessoDTO.getSenha() == null || acessoDTO.getSenha().length() != 6) {
            throw new IllegalArgumentException("Senha deve ter exatamente 6 caracteres");
        }
    }
}
