package com.backend.EasyPark.entities;

import com.backend.EasyPark.enums.TipoVeiculo;
import jakarta.persistence.*;
import com.backend.EasyPark.enums.TipoPlano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

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

    private LocalDateTime dataPagamento;

    private LocalDateTime dataVencimento;

    private boolean Status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")  // Especifica a coluna de associação
    private Usuario usuario;

    private double valorPlano;


} 