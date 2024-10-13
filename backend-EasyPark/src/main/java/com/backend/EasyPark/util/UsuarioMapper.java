package com.backend.EasyPark.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Usuario;


@Component
public class UsuarioMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
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
        usuario.setPagamentoPendente(dto.isPagamentoPendente());
        
        if (dto.getEnderecoDTO() != null) {
            usuario.setEndereco(enderecoMapper.toEntity(dto.getEnderecoDTO()));
        }
        
        if (dto.getVeiculoDTO() != null) {
            usuario.setVeiculo(veiculoMapper.toEntity(dto.getVeiculoDTO()));
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
        dto.setPagamentoPendente(entity.isPagamentoPendente());
        
        if (entity.getEndereco() != null) {
            dto.setEnderecoDTO(enderecoMapper.toDTO(entity.getEndereco()));
        }
        
        if (entity.getVeiculo() != null) {
            dto.setVeiculoDTO(veiculoMapper.toDTO(entity.getVeiculo()));
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
        usuario.setPagamentoPendente(dto.isPagamentoPendente());
        
        if (dto.getEnderecoDTO() != null) {
            if (usuario.getEndereco() == null) {
                usuario.setEndereco(enderecoMapper.toEntity(dto.getEnderecoDTO()));
            } else {
                enderecoMapper.updateEnderecoFromDTO(usuario.getEndereco(), dto.getEnderecoDTO());
            }
        } else {
            usuario.setEndereco(null);
        }
        
        if (dto.getVeiculoDTO() != null) {
            if (usuario.getVeiculo() == null) {
                usuario.setVeiculo(veiculoMapper.toEntity(dto.getVeiculoDTO()));
            } else {
                veiculoMapper.updateVeiculoFromDTO(usuario.getVeiculo(), dto.getVeiculoDTO());
            }
        } else {
            usuario.setVeiculo(null);
        }
    }
}
