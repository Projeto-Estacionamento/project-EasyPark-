<<<<<<< Updated upstream
/*
package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;



public class UsuarioMapper {


    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

=======
package com.backend.EasyPark.util;
import com.backend.EasyPark.model.dto.UsuarioDTO;
import com.backend.EasyPark.model.entities.Usuario;
import java.util.List;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
>>>>>>> Stashed changes
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setCpf(dto.getCpf());

        // Converte o Endereco
        if (dto.getEndereco() != null) {
            usuario.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        }
<<<<<<< Updated upstream





=======
        /*if (dto.getPlanoDTO() != null) {
            usuario.setPlano(PlanoMapper.convertToEntity(dto.getPlanoDTO()));
        }*/
>>>>>>> Stashed changes
        return usuario;
    }

    // Converte uma entidade Usuario para UsuarioDTO
<<<<<<< Updated upstream
    public UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }

=======
    public static UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }
>>>>>>> Stashed changes
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setCpf(entity.getCpf());

        // Converte o Endereco
        if (entity.getEndereco() != null) {
            dto.setEndereco(EnderecoMapper.toDTO(entity.getEndereco()));
        }

<<<<<<< Updated upstream
        if (dto.getVeiculosDTO() != null && !dto.getVeiculosDTO().isEmpty()) {
            List<Veiculo> veiculo = dto.getVeiculosDTO().stream()
                    .map(VeiculoMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setVeiculos(veiculo);
        }

        if (dto.getPlanosDTO() != null && !dto.getPlanosDTO().isEmpty()) {
            List<Plano> planos = dto.getPlanosDTO().stream()
                    .map(PlanoMapper::convertToEntity)
                    .collect(Collectors.toList());
            entity.setPlanos(planos);
        }

        // Converte os Veiculos

        // Converte os Planos

        return dto;
    }

    // Atualiza os dados do Usuario a partir de um UsuarioDTO
    public static void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getNome() != null) {
            usuario.setNome(usuarioDTO.getNome());
        }
        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getTelefone() != null) {
            usuario.setTelefone(usuarioDTO.getTelefone());
        }
        if (usuarioDTO.getCpf() != null) {
            usuario.setCpf(usuarioDTO.getCpf());
        }

        // Atualiza o Endereco, se presente no DTO
        if (usuarioDTO.getEndereco() != null) {
            usuario.setEndereco(EnderecoMapper.toEntity(usuarioDTO.getEndereco()));
        }

        // Atualiza os Veiculos, se presente no DTO

        // Atualiza os Planos, se presente no DTO
    }

    // Converte uma lista de Usuario para uma lista de UsuarioDTO
   public List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Converte uma lista de UsuarioDTO para uma lista de Usuario (entidade)
    public List<Usuario> toEntityList(List<UsuarioDTO> usuarioDtos) {
        return usuarioDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }


}*/
=======
        if (entity.getAssinaturas() != null) {
            dto.setAssinaturas(AssinaturaPlanoMapper.toDTOList(entity.getAssinaturas()));
        }

        if (entity.getVeiculos() != null && !entity.getVeiculos().isEmpty()) {
            dto.setVeiculosDTO(VeiculoMapper.toDtoList(entity.getVeiculos()));
        }

        return dto;
    }

    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toDTO).toList();
    }

    // Converte uma lista de UsuarioDTO para uma lista de Usuario (entidade)
    public static List<Usuario> toEntityList(List<UsuarioDTO> usuarioDtos) {
        return usuarioDtos.stream().map(UsuarioMapper::toEntity).toList();
    }

}
>>>>>>> Stashed changes
