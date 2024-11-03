package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Plano;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanoMapper {

    private final UsuarioPlanoMapper usuarioPlanoMapper;

    public PlanoMapper(UsuarioPlanoMapper usuarioPlanoMapper) {
        this.usuarioPlanoMapper = usuarioPlanoMapper;
    }

    public Plano toEntity(PlanoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Plano(dto.getId(),
                dto.getTipoPlano(),
                dto.getTipoVeiculo(),
                dto.getValorPlano(),
                usuarioPlanoMapper.toEntity(dto.getUsuarioPlanoDTOList()));
    }

    public PlanoDTO toDTO(Plano entity) {
        if (entity == null) {
            return null;
        }
        return new PlanoDTO(entity.getId(),
                entity.getTipoPlano(),
                entity.getTipoVeiculo(),
                entity.getValorPlano(),
                usuarioPlanoMapper.toDTO(entity.getUsuariosPlanos()));
    }

    public List<Plano> toListEntity(List<PlanoDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<PlanoDTO> toListDTO(List<Plano> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

