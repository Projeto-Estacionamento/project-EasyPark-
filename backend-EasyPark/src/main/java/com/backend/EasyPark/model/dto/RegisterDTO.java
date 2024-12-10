package com.backend.EasyPark.model.dto;

import com.backend.EasyPark.model.enums.TipoAcesso;

public record RegisterDTO (String email, String senha, String nome, TipoAcesso tipoAcesso) {

}