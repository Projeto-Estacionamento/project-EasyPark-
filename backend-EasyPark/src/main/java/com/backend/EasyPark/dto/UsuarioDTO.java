package com.backend.EasyPark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private boolean pagamentoPendente;
    private EnderecoDTO enderecoDTO;
    private List<VeiculoDTO> veiculosDTO;
}
