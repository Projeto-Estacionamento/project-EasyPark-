package com.backend.EasyPark.dto;

import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.enums.TipoPlano;
import com.backend.EasyPark.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
