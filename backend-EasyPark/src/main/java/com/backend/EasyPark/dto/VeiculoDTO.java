package com.backend.EasyPark.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {
    private Long id;
    private String placa;
    private String tipoVeiculo;
    private boolean ocpVaga;

    
}
