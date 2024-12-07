package com.backend.EasyPark.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfiguracaoSistema {

   @Id
   private Integer id = 1;

   private int qtdMoto;
   private int qtdCarro;
   private double valorHoraMoto;
   private double valorHoraCarro;
   private double valorDiariaCarro;
   private double valorDiariaMoto;
   private double horaMaximaAvulso;


}