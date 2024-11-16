package com.backend.EasyPark.entities;

import com.backend.EasyPark.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.backend.EasyPark.enums.TipoPlano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    private double valorPlano;

    // Remover campos que agora estarão em AssinaturaPlano
    // private LocalDateTime dataPagamento;
    // private LocalDateTime dataVencimento;
    // private boolean Status;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AssinaturaPlano> assinaturas;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    private LocalDateTime dataPagamento;

    private LocalDateTime dataVencimento;

    private boolean Status;

    private double valorPlano;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, orphanRemoval = true)
    @Lazy
    @JsonIgnore
    private List<Usuario> usuarios;*/



} 