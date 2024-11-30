package com.backend.EasyPark.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.backend.EasyPark.enums.TipoTicket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Integer id;
    private String placaVeiculo;
    private LocalDateTime horaChegada;
    private LocalDateTime horaSaida;
    private Duration totalHoras;
    private TipoTicket tipoTicket;
    private double valorTotalPagar;

