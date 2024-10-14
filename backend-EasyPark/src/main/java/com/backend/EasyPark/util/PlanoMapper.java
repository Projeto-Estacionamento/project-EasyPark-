package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Plano;

import java.util.List;
import java.util.stream.Collectors;

public class PlanoMapper {

    // Converte PlanoDTO para Plano
    public Plano toEntity(PlanoDTO dto) {
        if (dto == null) {
            return null;
        }

        Plano plano = new Plano();
        plano.setId(dto.getId());
        plano.setTipoPlano(dto.getTipoPlano());
        plano.setValorMensal(dto.getValorMensal());

        // Aqui pode associar o usuário, porem vai ser feito no service como boa pratica.
        // Usuario usuario = new Usuario();
        // usuario.setId(dto.getUsuarioId());
        // plano.setUsuario(usuario);

        return plano;
    }

    // Converte Plano para PlanoDTO
    public PlanoDTO toDTO(Plano plano) {
        if (plano == null) {
            return null;
        }

        PlanoDTO dto = new PlanoDTO();
        dto.setId(plano.getId());
        dto.setTipoPlano(plano.getTipoPlano());
        dto.setValorMensal(plano.getValorMensal());
        dto.setIdUsuario(plano.getUsuario() != null ? plano.getUsuario().getId() : null);

        return dto;
    }

    // Converte uma lista de Planos para uma lista de PlanoDTOs
    public List<PlanoDTO> toDTOList(List<Plano> planos) {
        return planos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}