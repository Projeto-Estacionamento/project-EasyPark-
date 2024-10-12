package com.backend.EasyPark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {
    private Long id;
    private String placa;
    private String tipoVeiculo;
    private boolean ocupandoVaga;
    private FabricanteDTO fabricanteDTO;
}
