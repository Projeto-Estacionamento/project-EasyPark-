package com.backend.EasyPark.dto;

import com.backend.EasyPark.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPlanoDTO {
    private Integer id;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento;
    private boolean Status;
    private UsuarioDTO usuario; //  usuário associado
    private PlanoDTO plano; //  plano associado
}