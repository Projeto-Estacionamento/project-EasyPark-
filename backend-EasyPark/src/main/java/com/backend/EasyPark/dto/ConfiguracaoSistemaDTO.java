package com.backend.EasyPark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoSistemaDTO {

    private Integer id;
    private int qtdMoto;
    private int qtdCarro;
    private double valorHoraMoto;
    private double valorHoraCarro;
    private double valorDiariaCarro;
    private double valorDiariaMoto;
    private double valorMensalidadeMoto;
    private double valorMensalidadeCarro;
    private double horaMaximaAvulso;
}
