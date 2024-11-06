package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.dto.UsuarioPlanoDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.UsuarioPlano;
import com.backend.EasyPark.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private VeiculoMapper veiculoMapper;

    @Autowired
    private UsuarioPlanoMapper usuarioPlanoMapper;

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setCpf(dto.getCpf());

        usuario.setEndereco(enderecoMapper.toEntity(dto.getEnderecoDTO()));

        // Mapeia os veículos
        if (dto.getVeiculosDTO() != null) {
            List<Veiculo> veiculos = veiculoMapper.toEntity(dto.getVeiculosDTO());
            usuario.setVeiculos(veiculos);
        } else {
            usuario.setVeiculos(null); // Define como null se não houver veículos
        }

        // Mapeia os planos
        if (dto.getUsuarioPlanosDto() != null) {
            List<UsuarioPlano> usuarioPlanos = usuarioPlanoMapper.toEntity(dto.getUsuarioPlanosDto()); //CRIAR LISTA DE MAPPER
            usuario.setUsuarioPlanos(usuarioPlanos);
        } else {
            usuario.setUsuarioPlanos(null); // Define como null se não houver planos
        }

        return usuario;
    }

    public UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setCpf(entity.getCpf());
        // dto.setPagamentoPendente(entity.isPagamentoPendente());

        // Mapeia o endereço
        if (entity.getEndereco() != null) {
            dto.setEnderecoDTO(enderecoMapper.toDTO(entity.getEndereco()));
        }

        // Mapeia os veículos
        if (entity.getVeiculos() != null) {
            List<VeiculoDTO> veiculosDTO = veiculoMapper.toDTO(entity.getVeiculos());
            dto.setVeiculosDTO(veiculosDTO);
        }

        // Mapeia os planos
        if (entity.getUsuarioPlanos() != null) {
            List<UsuarioPlanoDTO> planosDTO = usuarioPlanoMapper.toDTO(entity.getUsuarioPlanos()); // CRIAR LISTA MAPPER

            dto.setUsuarioPlanosDto(planosDTO);
        }

        return dto;
    }

    public void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getNome() != null) {
            usuario.setNome(usuarioDTO.getNome());
        }
        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getCpf() != null) {
            usuario.setCpf(usuarioDTO.getCpf());
        }
        // Continue atualizando outros campos conforme necessário
    }
}
