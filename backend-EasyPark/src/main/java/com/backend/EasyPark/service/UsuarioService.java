package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Endereco;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private VeiculoService veiculoService;

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        validarUsuario(usuarioDTO);
        if (usuarioDTO.getVeiculoDTO() != null) {
            veiculoService.validarVeiculo(usuarioDTO.getVeiculoDTO());
        }
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        validarUsuario(usuarioDTO);
        if (usuarioDTO.getVeiculoDTO() != null) {
            veiculoService.validarVeiculo(usuarioDTO.getVeiculoDTO());
        }
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    updateUsuarioFromDTO(usuario, usuarioDTO);
                    return convertToDTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setPagamentoPendente(usuarioDTO.isPagamentoPendente());
        
        if (usuarioDTO.getEnderecoDTO() != null) {
            usuario.setEndereco(enderecoService.convertToEntity(usuarioDTO.getEnderecoDTO()));
        }
        
        if (usuarioDTO.getVeiculoDTO() != null) {
            usuario.setVeiculo(veiculoService.convertToEntity(usuarioDTO.getVeiculoDTO()));
        }
        
        return usuario;
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setPagamentoPendente(usuario.isPagamentoPendente());
        
        if (usuario.getEndereco() != null) {
            usuarioDTO.setEnderecoDTO(enderecoService.convertToDTO(usuario.getEndereco()));
        }
        
        if (usuario.getVeiculo() != null) {
            usuarioDTO.setVeiculoDTO(veiculoService.convertToDTO(usuario.getVeiculo()));
        }
        
        return usuarioDTO;
    }

    private void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setPagamentoPendente(usuarioDTO.isPagamentoPendente());
        
        if (usuarioDTO.getEnderecoDTO() != null) {
            if (usuario.getEndereco() == null) {
                usuario.setEndereco(new Endereco());
            }
            enderecoService.updateEnderecoFromDTO(usuario.getEndereco(), usuarioDTO.getEnderecoDTO());
        }
        
        if (usuarioDTO.getVeiculoDTO() != null) {
            if (usuario.getVeiculo() == null) {
                usuario.setVeiculo(new Veiculo());
            }
            veiculoService.updateVeiculoFromDTO(usuario.getVeiculo(), usuarioDTO.getVeiculoDTO());
        } else {
            usuario.setVeiculo(null);
        }
    }

    private void validarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio");
        }
        if (usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (usuarioDTO.getCpf() == null || !usuarioDTO.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            throw new IllegalArgumentException("CPF inválido. Use o formato: XXX.XXX.XXX-XX");
        }
    }
}
