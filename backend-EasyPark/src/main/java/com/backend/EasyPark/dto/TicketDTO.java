package com.backend.EasyPark.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private int id;
    private int placaVeiculo;
    private LocalDateTime horaChegada;
    private LocalDateTime horaSaida;
    private LocalDateTime totalHoras;
    private double valorTotalPagar;
}
