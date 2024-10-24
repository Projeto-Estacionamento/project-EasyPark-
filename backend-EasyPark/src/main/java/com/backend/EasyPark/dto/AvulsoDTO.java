package com.backend.EasyPark.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvulsoDTO {

    private Long id;
    private String placaVeiculo;
    private LocalDateTime horaChegada;
    private LocalDateTime horaSaida;
    private LocalDateTime qtdHora;
    private double valorTotal;
}
