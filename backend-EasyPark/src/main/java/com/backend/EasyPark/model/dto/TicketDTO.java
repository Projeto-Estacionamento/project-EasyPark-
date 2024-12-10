package com.backend.EasyPark.model.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.backend.EasyPark.model.enums.TipoTicket;
import com.backend.EasyPark.model.enums.TipoVeiculo;
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
    private TipoVeiculo tipoVeiculo;
    private double valorTotalPagar;

}
