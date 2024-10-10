package com.backend.EasyPark.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String tipoVeiculo;  //se ele é moto ou carro
    private boolean ocupandoVaga;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

}
