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
    private TipoPlano tipoPlano;  // Usando String ao invés de TipoPlano para transferir os dados
    private TipoVeiculo tipoVeiculo;  // Usando String ao invés de TipoVeiculo
    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento;
    private boolean status;
    private UsuarioDTO usuarioDTO;  // ID do usuário (não a entidade completa)
    private double valorPlano;

}