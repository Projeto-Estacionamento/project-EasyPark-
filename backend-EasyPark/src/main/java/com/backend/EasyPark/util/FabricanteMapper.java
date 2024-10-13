package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.entities.Fabricante;

@Component
public class FabricanteMapper {

    public Fabricante toEntity(FabricanteDTO dto) {
        if (dto == null) {
            return null;
        }

        Fabricante fabricante = new Fabricante();
        fabricante.setId(dto.getId());
        fabricante.setModelo(dto.getModelo());
        fabricante.setMarca(dto.getMarca());
        fabricante.setAno(dto.getAno());

        return fabricante;
    }

    public FabricanteDTO toDTO(Fabricante entity) {
        if (entity == null) {
            return null;
        }

        return new FabricanteDTO(
            entity.getId(),
            entity.getModelo(),
            entity.getMarca(),
            entity.getAno()
        );
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
