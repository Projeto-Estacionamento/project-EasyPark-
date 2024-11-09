package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanoMapper {

    // Converte de Plano para PlanoDTO
    public PlanoDTO toDTO(Plano plano) {
        if (plano == null) {
            return null;
        }
        PlanoDTO dto = new PlanoDTO();
        dto.setId(plano.getId());
        dto.setTipoPlano(plano.getTipoPlano());
        dto.setTipoVeiculo(plano.getTipoVeiculo());
        dto.setDataPagamento(plano.getDataPagamento());
        dto.setDataVencimento(plano.getDataVencimento());
        dto.setStatus(plano.isStatus());

        // Convertendo o usuário para seu ID (apenas o ID)
        dto.setIdUsuarioDTO(plano.getUsuario() != null ? plano.getUsuario().getId() : null);

        dto.setValorPlano(plano.getValorPlano());

        return dto;
    }

    // Converte de PlanoDTO para Plano
    public Plano toEntity(PlanoDTO planoDTO) {
        if (planoDTO == null) {
            return null;
        }

        Plano plano = new Plano();
        plano.setId(planoDTO.getId());
        plano.setTipoPlano(planoDTO.getTipoPlano());
        plano.setTipoVeiculo(planoDTO.getTipoVeiculo());
        plano.setDataPagamento(planoDTO.getDataPagamento());
        plano.setDataVencimento(planoDTO.getDataVencimento());
        plano.setStatus(planoDTO.isStatus());
        plano.setValorPlano(planoDTO.getValorPlano());

        // Verifica e mapeia o Usuario, se presente
        if (planoDTO.getIdUsuarioDTO() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(planoDTO.getIdUsuarioDTO());  // Setando o ID do Usuário diretamente
            plano.setUsuario(usuario);
        }

        return plano;
    }

    public List<PlanoDTO> toDTOList(List<Plano> planos) {
        if (planos == null || planos.isEmpty()) {
            return null;
        }
        return planos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Converte uma lista de PlanoDTO para uma lista de Plano
    public List<Plano> toEntityList(List<PlanoDTO> planoDTOs) {
        if (planoDTOs == null || planoDTOs.isEmpty()) {
            return null;
        }
        return planoDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDTO(PlanoDTO planoDTO, Plano plano) {
        if (planoDTO == null || plano == null) {
            return;
        }

        // Atualiza apenas os campos do DTO que não são nulos
        if (planoDTO.getTipoPlano() != null) {
            plano.setTipoPlano(planoDTO.getTipoPlano());
        }
        if (planoDTO.getTipoVeiculo() != null) {
            plano.setTipoVeiculo(planoDTO.getTipoVeiculo());
        }
        if (planoDTO.getDataPagamento() != null) {
            plano.setDataPagamento(planoDTO.getDataPagamento());
        }
        if (planoDTO.getDataVencimento() != null) {
            plano.setDataVencimento(planoDTO.getDataVencimento());
        }
        if (planoDTO.isStatus() != plano.isStatus()) {
            plano.setStatus(planoDTO.isStatus());
        }
        if (planoDTO.getValorPlano() != 0) {
            plano.setValorPlano(planoDTO.getValorPlano());
        }

        // Atualiza o usuário associado, se presente no DTO
        if (planoDTO.getIdUsuarioDTO() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(planoDTO.getIdUsuarioDTO());  // Definir apenas o ID do usuário
            plano.setUsuario(usuario);
        }
    }


}
