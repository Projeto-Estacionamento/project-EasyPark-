package com.backend.EasyPark.util;
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Usuario;
import java.util.List;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
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
        /*if (dto.getPlanoDTO() != null) {
            usuario.setPlano(PlanoMapper.convertToEntity(dto.getPlanoDTO()));
        }*/
        return usuario;
    }

    // Converte uma entidade Usuario para UsuarioDTO
    public static UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }
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


 /*   //Método de conversão de DTO para entidade
    public static Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setCpf(usuarioDTO.getCpf());

        if (usuarioDTO.getEndereco() != null) {
            Endereco endereco = EnderecoMapper.toEntity(usuarioDTO.getEndereco());
            usuario.setEndereco(endereco);
        }

        if (usuarioDTO.getVeiculosDTO() != null && !usuarioDTO.getVeiculosDTO().isEmpty()) {
            List<Veiculo> veiculo = usuarioDTO.getVeiculosDTO().stream()
                    .map(VeiculoMapper::toEntity)
                    .collect(Collectors.toList());
            usuario.setVeiculos(veiculo);
        }

        if (usuarioDTO.getPlanosDTO() != null && !usuarioDTO.getPlanosDTO().isEmpty()) {
            List<Plano> planos = usuarioDTO.getPlanosDTO().stream()
                    .map(PlanoMapper::convertToEntity)
                    .collect(Collectors.toList());
            usuario.setPlanos(planos);
        }

        return usuario;
    }

    // Método de conversão de entidade para DTO
    public static UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setCpf(usuario.getCpf());

        if (usuario.getEndereco() != null) {
            EnderecoDTO enderecoDTO = EnderecoMapper.toDTO(usuario.getEndereco());
            usuarioDTO.setEndereco(enderecoDTO);
        }

        // Mapeia os veículos para veiculosDTO
        usuarioDTO.setVeiculosDTO(usuario.getVeiculos() != null
                ? usuario.getVeiculos().stream()
                .map(VeiculoMapper::toDTO)
                .collect(Collectors.toList())
                : null);

        // Mapeia os planos para planosDTO
        usuarioDTO.setPlanosDTO(usuario.getPlanos() != null
                ? usuario.getPlanos().stream()
                .map(PlanoMapper::convertToDTO)
                .collect(Collectors.toList())
                : null);


        return usuarioDTO;
    }

    // Método para atualizar dados de um usuário a partir de um DTO
    private void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setCpf(usuarioDTO.getCpf());

        if (usuarioDTO.getEndereco() != null) {
            if (usuario.getEndereco() == null) {
                usuario.setEndereco(new Endereco());
            }
            EnderecoMapper.updateEnderecoFromDTO(usuario.getEndereco(), usuarioDTO.getEndereco());
        }

    }*/

}
