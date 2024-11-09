/*
package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.UsuarioPlanoDTO;
import com.backend.EasyPark.entities.UsuarioPlano;
import com.backend.EasyPark.repository.UsuarioPlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


@Component
public class UsuarioPlanoMapper {

    @Autowired
    @Lazy
    private PlanoMapper planoMapper;


    public List<UsuarioPlano> toEntityList(List<UsuarioPlanoDTO> dtos) {
        List<UsuarioPlano> usuarioPlanos = new ArrayList<>();
        for (UsuarioPlanoDTO dto : dtos) {
            usuarioPlanos.add(toEntity(dto));
        }
        return usuarioPlanos;
    }

    public List<UsuarioPlanoDTO> toDTO(List<UsuarioPlano> entities) {
        if (entities == null) {
            return null;
        }

        List<UsuarioPlanoDTO> dtos = new ArrayList<>();
        for (UsuarioPlano entity : entities) {
            dtos.add(toDTO(entity));
        }

        return dtos;
    }

    public UsuarioPlano toEntity(UsuarioPlanoDTO dto) {
        if (dto == null) {
            return null;
        }

        UsuarioPlano usuarioPlano = new UsuarioPlano();
        usuarioPlano.setId(dto.getId());
        usuarioPlano.setDataPagamento(dto.getDataPagamento());
        usuarioPlano.setDataVencimento(dto.getDataVencimento());
        usuarioPlano.setStatus(dto.isStatus());

        // Não há mais a chamada para UsuarioMapper.toEntity aqui

        if (dto.getPlano() != null) {
            usuarioPlano.setPlano(planoMapper.toEntity(dto.getPlano()));
        }

        return usuarioPlano;
    }

    public UsuarioPlanoDTO toDTO(UsuarioPlano entity) {
        if (entity == null) {
            return null;
        }

        UsuarioPlanoDTO dto = new UsuarioPlanoDTO();
        dto.setId(entity.getId());
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setStatus(entity.isStatus());

        // Não há mais a chamada para UsuarioMapper.toDTO aqui

        if (entity.getPlano() != null) {
            dto.setPlano(planoMapper.toDTO(entity.getPlano()));
        }

        return dto;
    }


    public void updateUsuarioPlanoFromDTO(List<UsuarioPlano> usuarioPlanos, List<UsuarioPlanoDTO> dtos) {
        for (int i = 0; i < dtos.size(); i++) {
            if (i < usuarioPlanos.size()) {
                UsuarioPlano usuarioPlano = usuarioPlanos.get(i);
                UsuarioPlanoDTO dto = dtos.get(i);

                usuarioPlano.setDataPagamento(dto.getDataPagamento());
                usuarioPlano.setDataVencimento(dto.getDataVencimento());
                usuarioPlano.setStatus(dto.isStatus());

                // Atualiza o plano associado
                if (dto.getPlano() != null) {
                    if (usuarioPlano.getPlano() == null) {
                        usuarioPlano.setPlano(planoMapper.toEntity(dto.getPlano()));
                    } else {
                        planoMapper.updatePlanoFromDTO(usuarioPlano.getPlano(), dto.getPlano());
                    }
                } else {
                    usuarioPlano.setPlano(null);
                }
            } else {
                // Adiciona novos usuarioPlanos se houver mais DTOs que entidades
                UsuarioPlano novoUsuarioPlano = toEntity(dtos.get(i));
                usuarioPlanos.add(novoUsuarioPlano);
            }
        }

        // Remove usuarioPlanos extras caso existam mais usuarioPlanos do que DTOs
        while (usuarioPlanos.size() > dtos.size()) {
            usuarioPlanos.remove(usuarioPlanos.size() - 1);
        }
    }
}
*/
