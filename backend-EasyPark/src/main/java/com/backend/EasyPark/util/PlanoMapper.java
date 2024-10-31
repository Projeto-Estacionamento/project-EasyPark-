package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.UsuarioPlano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Converte PlanoDTO para Plano
    public UsuarioPlano toEntity(PlanoDTO dto) {
        UsuarioPlano usuarioPlano = new UsuarioPlano(dto.getId(),dto.getDataVencimento(), dto.getDataPagamento()
        ,dto.getCategoriaPlano(),dto.getValorMensal(), usuarioMapper.toEntity(dto.getUsuarioDTO()));

        return usuarioPlano;
    }

    // Converte Plano para PlanoDTO
    public PlanoDTO toDTO(UsuarioPlano usuarioPlano) {
        PlanoDTO planoDTO = new PlanoDTO(usuarioPlano.getId(), usuarioPlano.getCategoriaPlano(), usuarioPlano.getDataPagamento()
                , usuarioPlano.getDataVencimento(), usuarioPlano.getValorMensal(), usuarioMapper.toDTO(usuarioPlano.getUsuario()));
        return planoDTO;
    }
    //Converte uma lista de Planos para uma lista de PlanoDTOs
    public List<PlanoDTO> toPlanoListDTO(List<UsuarioPlano> usuarioPlanos) {
        List<PlanoDTO> planoListDto = new ArrayList<PlanoDTO>();
        for (UsuarioPlano usuarioPlano : usuarioPlanos) {
            planoListDto.add(toDTO(usuarioPlano));
        }
        return planoListDto;
    }
    //
    public List<UsuarioPlano> toPlanoList(List<PlanoDTO> planosDTO) {
        List<UsuarioPlano> listaUsuarioPlano = new ArrayList<>();
        for (PlanoDTO plano : planosDTO) {
            listaUsuarioPlano.add(toEntity(plano)); // Método que converte PlanoDTO para Plano
        }
        return listaUsuarioPlano;
    }
}

