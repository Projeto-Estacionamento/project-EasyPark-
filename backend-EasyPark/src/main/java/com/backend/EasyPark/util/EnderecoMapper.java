package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.entities.Endereco;

@Component
public class EnderecoMapper {

    public static Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco(dto.getId(), dto.getCidade(), dto.getEstado(), dto.getCep());
        return endereco;
    }

    public static EnderecoDTO toDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco.getId(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
        return enderecoDTO;
    }

    public static void updateEnderecoFromDTO(Endereco endereco, EnderecoDTO dto) {
        if (dto == null) {
            return;
        }
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
    }
}
