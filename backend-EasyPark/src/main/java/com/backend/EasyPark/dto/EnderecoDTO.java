package com.backend.EasyPark.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String cidade;
    private String estado;
    private String cep;
}
