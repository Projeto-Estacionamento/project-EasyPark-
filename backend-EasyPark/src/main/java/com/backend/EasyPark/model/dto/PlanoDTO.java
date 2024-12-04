package com.backend.EasyPark.model.dto;

import com.backend.EasyPark.model.enums.TipoPlano;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {
    private Integer id;
    private TipoPlano tipoPlano;
    private TipoVeiculo tipoVeiculo;
    /*private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento;
    private boolean status;*/
    private double valorPlano;

}