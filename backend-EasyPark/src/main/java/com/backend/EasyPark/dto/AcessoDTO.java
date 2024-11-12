package com.backend.EasyPark.dto;

import com.backend.EasyPark.enums.TipoAcesso;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AcessoDTO {
    private String username;
    private String senha;
    private TipoAcesso tipoAcesso;

    // Construtores, getters e setters
    public AcessoDTO(String username, String senha, TipoAcesso tipoAcesso) {
        this.username = username;
        this.senha = senha;
        this.tipoAcesso = tipoAcesso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    
}
