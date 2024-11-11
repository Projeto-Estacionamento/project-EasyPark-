package com.backend.EasyPark.util.validacao;

import com.backend.EasyPark.dto.UsuarioDTO;


public class ValidacaoUsuario {

    public void validarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio");
        }
        if (usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (usuarioDTO.getCpf() == null || !usuarioDTO.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            throw new IllegalArgumentException("CPF inválido. Use o formato: XXX.XXX.XXX-XX");
        }
    }



}
