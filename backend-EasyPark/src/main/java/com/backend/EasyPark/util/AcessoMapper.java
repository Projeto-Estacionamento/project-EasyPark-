package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.entities.Acesso;
import org.springframework.stereotype.Component;

@Component
public class AcessoMapper {

    public Acesso toEntity(AcessoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Acesso(dto.getUsername(), dto.getSenha(), dto.getTipoAcesso());
    }

    public AcessoDTO toDTO(Acesso entity) {
        if (entity == null) {
            return null;
        }
        return new AcessoDTO(entity.getUsername(), entity.getSenha(), entity.getTipoAcesso());
    }
}