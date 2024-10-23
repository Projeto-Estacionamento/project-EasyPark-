package com.backend.EasyPark.dto;


import com.backend.EasyPark.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarEntradaDTO {

    private boolean sucesso;
    private String mensagem;
    private Ticket ticket;
}
