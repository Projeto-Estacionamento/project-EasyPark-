package com.backend.EasyPark.util;

import com.backend.EasyPark.model.dto.AcessoDTO;
import com.backend.EasyPark.model.entities.Acesso;
import org.springframework.stereotype.Component;

@Component
public class AcessoMapper {

    public static Acesso toEntity(AcessoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Acesso(dto.getId(),dto.getUsername(), dto.getSenha(), dto.getEmail(), dto.getTipoAcesso());
    }

    public static AcessoDTO toDTO(Acesso entity) {
        if (entity == null) {
            return null;
        }
        return new AcessoDTO(entity.getId(),entity.getUsername(), entity.getSenha(), entity.getEmail(), entity.getTipoAcesso());
    }
}
