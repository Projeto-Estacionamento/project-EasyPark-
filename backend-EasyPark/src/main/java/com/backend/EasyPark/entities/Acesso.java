package com.backend.EasyPark.entities;

import com.backend.EasyPark.enums.TipoAcesso;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Acesso {
    @Id
    private Long id;

    private String username;
    private String senha;
    private TipoAcesso tipoAcesso;

    // Construtor padrão
    public Acesso() {
    }

    // Construtor com parâmetros
    public Acesso(String username, String senha, TipoAcesso tipoAcesso) {
        this.username = username;
        this.senha = senha;
        this.tipoAcesso = tipoAcesso;
    }

    // Getters e Setters
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
