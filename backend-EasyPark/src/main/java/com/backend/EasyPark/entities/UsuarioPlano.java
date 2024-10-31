package com.backend.EasyPark.entities;


import com.backend.EasyPark.enums.CategoriaPlano;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPlano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento;

    @Enumerated(EnumType.STRING)
    private CategoriaPlano categoriaPlano; // Adicionar qual o tipo de veiculo para o plano se é carro ou moto


    private BigDecimal valorMensal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
