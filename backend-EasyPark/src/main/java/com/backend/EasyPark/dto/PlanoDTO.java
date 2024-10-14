package com.backend.EasyPark.dto;

import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.enums.TipoPlano;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDTO {
    private Long id;
    private TipoPlano tipoPlano; // Adicionar qual o tipo de veiculo para o plano se é carro ou moto
    private BigDecimal valorMensal;
    private Long idUsuario;

}
