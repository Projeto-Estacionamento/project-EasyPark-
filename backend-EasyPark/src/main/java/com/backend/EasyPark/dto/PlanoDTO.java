package com.backend.EasyPark.dto;

import com.backend.EasyPark.enums.TipoPlano;
import com.backend.EasyPark.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDTO {
    private Integer id;
    private TipoPlano tipoPlano;
    private TipoVeiculo tipoVeiculo;
    private double valorPlano;
    private List<UsuarioPlanoDTO> usuarioPlanoDTOList;
}