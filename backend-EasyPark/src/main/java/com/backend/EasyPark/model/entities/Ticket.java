package com.backend.EasyPark.model.entities;



import com.backend.EasyPark.model.enums.TipoTicket;

import com.backend.EasyPark.model.enums.TipoVeiculo;
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

    @Column(nullable = false)
    private String placaVeiculo;

    private LocalDateTime horaChegada;
    private LocalDateTime horaSaida;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipoTicket;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    private Duration totalHoras;
    private double valorTotalPagar;

}
