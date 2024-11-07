package com.backend.EasyPark.entities;


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

    private LocalDateTime dataPagamento; //CRIAR LOGICA:quando for pago, tem que fazer o preenchimento desta variavel
    private LocalDateTime dataVencimento; //Aqui é preciso definir uma DataVencimento, que vai ser de acordo do dia que foi pago o plano

    private boolean Status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private Plano plano;
}
