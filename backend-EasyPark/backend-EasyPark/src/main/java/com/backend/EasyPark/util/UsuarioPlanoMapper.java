package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.UsuarioPlanoDTO;
import com.backend.EasyPark.entities.UsuarioPlano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioPlanoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PlanoMapper planoMapper;

    public UsuarioPlano toEntity(UsuarioPlanoDTO dto) {
        if (dto == null) {
            return null;
        }
        UsuarioPlano usuarioPlano = new UsuarioPlano(dto.getId(),
                dto.getDataPagamento(),
                dto.getDataVencimento(),
                dto.isStatus(),
                usuarioMapper.toEntity(dto.getUsuario()),
                planoMapper.toEntity(dto.getPlano()));

        return usuarioPlano;
    }

    public UsuarioPlanoDTO toDTO(UsuarioPlano entity) {
        if (entity == null) {
            return null;
        }
        UsuarioPlanoDTO dto = new UsuarioPlanoDTO(
                entity.getId(),
                entity.getDataPagamento(),
                entity.getDataVencimento(),
                entity.isStatus(),
                usuarioMapper.toDTO(entity.getUsuario()),
                planoMapper.toDTO(entity.getPlano()));

        return dto;
    }

    public List<UsuarioPlanoDTO> toDTO(List<UsuarioPlano> entityList) {
        if (entityList == null) {
            return null;
        }
        List<UsuarioPlanoDTO> dtoList = new ArrayList<>();

        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<UsuarioPlano> toEntity(List<UsuarioPlanoDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
