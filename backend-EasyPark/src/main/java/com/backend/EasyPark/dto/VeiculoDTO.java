package com.backend.EasyPark.dto;

import com.backend.EasyPark.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {
    private Long id;
    private String placa;
    private TipoVeiculo tipoVeiculo;
    private boolean ocupandoVaga;
    private FabricanteDTO fabricanteDTO;
}
