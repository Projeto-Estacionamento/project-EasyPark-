/*
package com.backend.EasyPark.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPlano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario; // Referência ao usuário do plano

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;
}

*/
