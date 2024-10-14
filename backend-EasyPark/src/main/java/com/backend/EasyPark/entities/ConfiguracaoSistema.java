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
   private Long id = 1L;

   private int qtdMoto;
   private int qtdCarro;
   private double valorHoraMoto;
   private double valorHoraCarro;
   private double valorDiariaCarro;
   private double valorDiariaMoto;
   private double horaMaximaAvulso;

//    qtdMoto: Quantidade total de vagas para motos.
//    qtdCarro: Quantidade total de vagas para carros.
//    valorHoraMoto:
//    valorHoraCarro:
//    valorDiariaCarro:
//    ValorDiariaMoto:
//    valorMaximo: caso passar do horario definido, será cobrado como diaria.

}