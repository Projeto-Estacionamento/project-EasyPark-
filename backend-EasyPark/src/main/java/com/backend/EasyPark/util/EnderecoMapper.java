package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.entities.Endereco;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        return endereco;
    }

    public EnderecoDTO toDTO(Endereco entity) {
        if (entity == null) {
            return null;
        }

        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(entity.getId());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());
        return dto;
    }

    public void updateEntityFromDTO(EnderecoDTO dto, Endereco entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
    }

    public void updateEnderecoFromDTO(Endereco endereco, EnderecoDTO dto) {
        if (dto == null) {
            return;
        }
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
    }
}
