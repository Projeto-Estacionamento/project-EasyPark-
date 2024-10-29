package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.enums.TipoPlano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlanoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Converte PlanoDTO para Plano
    public Plano toEntity(PlanoDTO dto) {
        Plano plano = new Plano(dto.getId(),dto.getDataVencimento(), dto.getDataPagamento()
        ,dto.getTipoPlano(),dto.getValorMensal(), usuarioMapper.toEntity(dto.getUsuarioDTO()));

        return plano;
    }

    // Converte Plano para PlanoDTO
    public PlanoDTO toDTO(Plano plano) {
        PlanoDTO planoDTO = new PlanoDTO(plano.getId(),plano.getTipoPlano(), plano.getDataPagamento()
                ,plano.getDataVencimento(),plano.getValorMensal(), usuarioMapper.toDTO(plano.getUsuario()));
        return planoDTO;
    }
    // Converte uma lista de Planos para uma lista de PlanoDTOs
    public List<PlanoDTO> toEnderecoDtoList(List<Plano> planos) {
        List<PlanoDTO> planoListDto = new ArrayList<PlanoDTO>();
        for (Plano plano : planos) {
            planoListDto.add(toDTO(plano));
        }
        return planoListDto;
    }

    public List<Plano> toEnderecoList(List<PlanoDTO> planosDTO) {
        List<Plano> listaPlano = new ArrayList<Plano>();
        for (PlanoDTO plano : planosDTO) {
            listaPlano.add(toEntity(plano));
        }
        return listaPlano;
    }
}

