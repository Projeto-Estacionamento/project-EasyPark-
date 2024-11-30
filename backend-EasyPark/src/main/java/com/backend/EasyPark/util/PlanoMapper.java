package com.backend.EasyPark.util;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
=======
import com.backend.EasyPark.model.dto.PlanoDTO;
import com.backend.EasyPark.model.entities.Plano;
import org.springframework.stereotype.Component;

import java.util.List;
>>>>>>> Stashed changes

@Component
public class PlanoMapper {

    // Converte de Plano para PlanoDTO
    public static Plano convertToEntity(PlanoDTO planoDTO) {
        if (planoDTO == null) {
            return null;
        }
        Plano plano = new Plano();
        plano.setId(planoDTO.getId());
        plano.setTipoPlano(planoDTO.getTipoPlano());
        plano.setTipoVeiculo(planoDTO.getTipoVeiculo());
<<<<<<< Updated upstream
        plano.setDataPagamento(planoDTO.getDataPagamento());
        plano.setDataVencimento(planoDTO.getDataVencimento());
        plano.setStatus(planoDTO.isStatus());
        plano.setValorPlano(planoDTO.getValorPlano());

        // Converte UsuarioDTO para Usuario de forma simplificada
        if (planoDTO.getUsuarioDTO() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(planoDTO.getUsuarioDTO().getId());
            usuario.setNome(planoDTO.getUsuarioDTO().getNome());
            usuario.setEmail(planoDTO.getUsuarioDTO().getEmail());
            usuario.setCpf(planoDTO.getUsuarioDTO().getCpf());
            plano.setUsuario(usuario);
        }

=======
        plano.setValorPlano(planoDTO.getValorPlano());

>>>>>>> Stashed changes
        return plano;
    }

    // Converte de Plano para PlanoDTO
    public static PlanoDTO convertToDTO(Plano plano) {
        if (plano == null) {
            return null;
        }
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        PlanoDTO planoDTO = new PlanoDTO();
        planoDTO.setId(plano.getId());
        planoDTO.setTipoPlano(plano.getTipoPlano());
        planoDTO.setTipoVeiculo(plano.getTipoVeiculo());
<<<<<<< Updated upstream
        planoDTO.setDataPagamento(plano.getDataPagamento());
        planoDTO.setDataVencimento(plano.getDataVencimento());
        planoDTO.setStatus(plano.isStatus());
        planoDTO.setValorPlano(plano.getValorPlano());

        // Converte Usuario para UsuarioDTO de forma simplificada
        if (plano.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(plano.getUsuario().getId());
            usuarioDTO.setNome(plano.getUsuario().getNome());
            usuarioDTO.setEmail(plano.getUsuario().getEmail());
            usuarioDTO.setTelefone(plano.getUsuario().getTelefone());
            usuarioDTO.setCpf(plano.getUsuario().getCpf());
            planoDTO.setUsuarioDTO(usuarioDTO);
        }
=======
        planoDTO.setValorPlano(plano.getValorPlano());

>>>>>>> Stashed changes

        return planoDTO;
    }

<<<<<<< Updated upstream
=======
    public static List<PlanoDTO> toDTOList(List<Plano> planos) {
        return planos.stream().map(PlanoMapper::convertToDTO).toList();
    }

    public static List<Plano> toPlanoList(List<PlanoDTO> planoDTOs) {
        return planoDTOs.stream().map(PlanoMapper::convertToEntity).toList();
    }

>>>>>>> Stashed changes
    // Método para atualizar dados de um plano
    public static void updatePlanoFromDTO(Plano plano, PlanoDTO planoDTO) {
        if (planoDTO == null || plano == null) {
            return;
        }
        plano.setTipoPlano(planoDTO.getTipoPlano());
        plano.setTipoVeiculo(planoDTO.getTipoVeiculo());
<<<<<<< Updated upstream
        plano.setDataPagamento(planoDTO.getDataPagamento());
        plano.setDataVencimento(planoDTO.getDataVencimento());
        plano.setStatus(planoDTO.isStatus());
        plano.setValorPlano(planoDTO.getValorPlano());

        if (planoDTO.getUsuarioDTO() != null) {
            if (plano.getUsuario() == null) {
                plano.setUsuario(new Usuario());
            }
            plano.getUsuario().setId(planoDTO.getUsuarioDTO().getId());
        }
    }

    public List<PlanoDTO> toDTOList(List<Plano> planos) {
        if (planos == null || planos.isEmpty()) {
            return new ArrayList<>();
        }

        List<PlanoDTO> planoDTOs = new ArrayList<>();
        for (Plano plano : planos) {
            planoDTOs.add(convertToDTO(plano));
        }
        return planoDTOs;
    }

    // Converte uma lista de PlanoDTO para uma lista de Plano
    public List<Plano> toEntityList(List<PlanoDTO> planoDTOs) {
        if (planoDTOs == null || planoDTOs.isEmpty()) {
            return new ArrayList<>();
        }

        List<Plano> planos = new ArrayList<>();
        for (PlanoDTO planoDTO : planoDTOs) {
            planos.add(convertToEntity(planoDTO));
        }
        return planos;
    }

}
=======
        plano.setValorPlano(planoDTO.getValorPlano());

    }


}
>>>>>>> Stashed changes
