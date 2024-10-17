package com.backend.EasyPark.dto;

import java.math.BigDecimal;

import com.backend.EasyPark.enums.TipoPlano;
import com.backend.EasyPark.enums.TipoVeiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDTO {
    private Long id;
    private TipoPlano tipoPlano;
    private TipoVeiculo tipoVeiculo;
    private BigDecimal valorMensal;
    private Long idUsuario;
}
