package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.AvulsoDTO;
import com.backend.EasyPark.entities.Avulso;

@Component
public class AvulsoMapper {

    public Avulso toEntity(AvulsoDTO dto) {
        if (dto == null) {
            return null;
        }

        Avulso avulso = new Avulso();
        avulso.setId(dto.getId());
        avulso.setPlacaVeiculo(dto.getPlacaVeiculo().toUpperCase());
        avulso.setHoraChegada(dto.getHoraChegada());
        avulso.setHoraSaida(dto.getHoraSaida());
        avulso.setQtdHora(dto.getQtdHora());
        avulso.setValorTotal(dto.getValorTotal());

        return avulso;
    }

    public AvulsoDTO toDTO(Avulso entity) {
        if (entity == null) {
            return null;
        }

        return new AvulsoDTO(
            entity.getId(),
            entity.getPlacaVeiculo(),
            entity.getHoraChegada(),
            entity.getHoraSaida(),
            entity.getQtdHora(),
            entity.getValorTotal()
        );
    }

    public void updateEntityFromDTO(Avulso avulso, AvulsoDTO dto) {
        if (dto == null) {
            return;
        }

        avulso.setPlacaVeiculo(dto.getPlacaVeiculo().toUpperCase());
        avulso.setHoraChegada(dto.getHoraChegada());
        avulso.setHoraSaida(dto.getHoraSaida());
        avulso.setQtdHora(dto.getQtdHora());
        avulso.setValorTotal(dto.getValorTotal());
    }
}
