package com.backend.EasyPark.util;

import com.backend.EasyPark.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Usuario;

import java.util.List;

@Component
public class UsuarioMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    @Lazy
    private VeiculoMapper veiculoMapper;

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
        //usuario.setPagamentoPendente(dto.isPagamentoPendente());

        // Mapeia o endereço
        if (dto.getEnderecoDTO() != null) {
            usuario.setEndereco(enderecoMapper.toEntity(dto.getEnderecoDTO()));
        }

        // Mapeia os veículos
        if (dto.getVeiculosDTO() != null) {
            List<Veiculo> veiculos = veiculoMapper.toEntity(dto.getVeiculosDTO());
            usuario.setVeiculos(veiculos);
        } else {
            usuario.setVeiculos(null); // Define como null se não houver veículos
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
            dto.setVeiculosDTO(veiculoMapper.toDTO(entity.getVeiculos()));
        } else {
            dto.setVeiculosDTO(null); // Define como null se não houver veículos
        }

        return dto;
    }

    public void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO dto) {
        if (dto == null) {
            return;
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setCpf(dto.getCpf());
       // usuario.setPagamentoPendente(dto.isPagamentoPendente());

        // Atualiza o endereço
        if (dto.getEnderecoDTO() != null) {
            if (usuario.getEndereco() == null) {
                usuario.setEndereco(enderecoMapper.toEntity(dto.getEnderecoDTO()));
            } else {
                enderecoMapper.updateEnderecoFromDTO(usuario.getEndereco(), dto.getEnderecoDTO());
            }
        } else {
            usuario.setEndereco(null);
        }

        // Atualiza os veículos
        if (dto.getVeiculosDTO() != null) {
            if (usuario.getVeiculos() == null) {
                usuario.setVeiculos(veiculoMapper.toEntity(dto.getVeiculosDTO()));
            } else {
                veiculoMapper.updateVeiculoFromDTO(usuario.getVeiculos(), dto.getVeiculosDTO());
            }
        } else {
            usuario.setVeiculos(null); // Define como null se não houver veículos
        }
    }
}
