package com.backend.EasyPark.model.dto;

import java.time.LocalDateTime;

import com.backend.EasyPark.model.enums.TipoTicket;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioTicketsFechadosDTO {

    private Integer id;
    private String placaVeiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private TipoVeiculo tipoVeiculo;
    private TipoTicket tipoTicket;
    private double valorTotalPagar;
}
