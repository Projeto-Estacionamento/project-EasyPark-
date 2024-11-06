package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.entities.Fabricante;

@Component
public class FabricanteMapper {

    public Fabricante toEntity(FabricanteDTO dto) {
        Fabricante fabricante = new Fabricante(dto.getId(), dto.getModelo(), dto.getMarca(),
                dto.getAno());
        return fabricante;
    }

    public FabricanteDTO toDTO(Fabricante entity) {
        FabricanteDTO dto = new FabricanteDTO(entity.getId(), entity.getModelo()
                , entity.getMarca(), entity.getAno());
        return dto;
    }

    public void updateEntityFromDTO(Fabricante fabricante, FabricanteDTO dto) {
        if (dto == null) {
            return;
        }

        fabricante.setModelo(dto.getModelo());
        fabricante.setMarca(dto.getMarca());
        fabricante.setAno(dto.getAno());
    }

}
