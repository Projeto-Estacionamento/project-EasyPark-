package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.entities.Fabricante;
=======
import com.backend.EasyPark.model.dto.FabricanteDTO;
import com.backend.EasyPark.model.entities.Fabricante;
>>>>>>> Stashed changes

@Component
public class FabricanteMapper {

    public static Fabricante toEntity(FabricanteDTO dto) {
<<<<<<< Updated upstream
        Fabricante fabricante = new Fabricante(dto.getId(), dto.getModelo(), dto.getMarca(),
                dto.getAno());
        return fabricante;
    }

    public static FabricanteDTO toDTO(Fabricante entity) {
        FabricanteDTO dto = new FabricanteDTO(entity.getId(), entity.getModelo()
                , entity.getMarca(), entity.getAno());
        return dto;
    }



=======
        return new Fabricante(dto.getId(), dto.getModelo(), dto.getMarca(),
                dto.getAno());
    }

    public static FabricanteDTO toDTO(Fabricante entity) {
        return new FabricanteDTO(entity.getId(), entity.getModelo()
                , entity.getMarca(), entity.getAno());
    }

>>>>>>> Stashed changes
    public static void updateEntityFromDTO(Fabricante fabricante, FabricanteDTO dto) {
        if (dto == null) {
            return;
        }
        fabricante.setModelo(dto.getModelo());
        fabricante.setMarca(dto.getMarca());
        fabricante.setAno(dto.getAno());
    }

<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
