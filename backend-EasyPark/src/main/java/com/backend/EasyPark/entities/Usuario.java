package com.backend.EasyPark.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "veiculos", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<UsuarioPlano> usuarioPlanos;

    public Usuario(Integer id, String nome, String email, String telefone, String cpf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculo.setUsuario(this);
        veiculos.add(veiculo);
    }

    public void adicionarPlano(UsuarioPlano usuarioPlano) {
        usuarioPlano.setUsuario(this);
        usuarioPlanos.add(usuarioPlano);
    }
}