package com.backend.EasyPark.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    //private boolean pagamentoPendente;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Plano> planos = new ArrayList<>(); // Alterado para List<Plano>


    public void adicionarVeiculo(Veiculo veiculo) {
        veiculo.setUsuario(this);
        veiculos.add(veiculo);
    }

    public void adicionarPlano(Plano plano) {
        plano.setUsuario(this);
        planos.add(plano);
    }
}