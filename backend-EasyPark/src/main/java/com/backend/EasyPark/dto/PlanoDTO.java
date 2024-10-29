package com.backend.EasyPark.dto;


import com.backend.EasyPark.enums.TipoPlano;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDTO {
    private Long id;
    private TipoPlano tipoPlano;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento; // Adicionar qual o tipo de veiculo para o plano se é carro ou moto
    private BigDecimal valorMensal;
    private UsuarioDTO usuarioDTO;

}