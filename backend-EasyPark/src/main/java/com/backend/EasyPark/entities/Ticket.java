package com.backend.EasyPark.entities;



import com.backend.EasyPark.enums.TipoTicket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placaVeiculo;
    private LocalDateTime horaChegada;
    private LocalDateTime horaSaida;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipoTicket;

    private Duration totalHoras;
    private double valorTotalPagar;

}
