package com.backend.EasyPark.dto;

import java.math.BigDecimal;

import com.backend.EasyPark.enums.TipoPlano;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDTO {
    private Long id;
    private TipoPlano tipoPlano; // Adicionar qual o tipo de veiculo para o plano se é carro ou moto
    private BigDecimal valorMensal;
    private Long idUsuario;

}