package com.backend.EasyPark.entities;

import com.backend.EasyPark.enums.TipoVeiculo;
import jakarta.persistence.*;
import com.backend.EasyPark.enums.TipoPlano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    private TipoVeiculo tipoVeiculo;

    private double valorPlano;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL)
    private List<UsuarioPlano> usuariosPlanos;
} 