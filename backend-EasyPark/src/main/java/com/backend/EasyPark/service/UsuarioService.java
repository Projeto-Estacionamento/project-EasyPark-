package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.EasyPark.exception.EstacionamentoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Endereco;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private PlanoService planoService;

    // Criar um novo usuário
    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        validarUsuario(usuarioDTO);
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    // Buscar um usuário pelo ID
    public UsuarioDTO buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Listar todos os usuários
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Atualizar um usuário
    @Transactional
    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDTO) {
        validarUsuario(usuarioDTO);
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    updateUsuarioFromDTO(usuario, usuarioDTO);
                    return convertToDTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deletar um usuário
    @Transactional
    public void deletarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    // Buscar usuários por CPF
    public UsuarioDTO buscarUsuarioPorCpf(String cpf) throws EstacionamentoException {
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if (usuario == null) {
            throw new EstacionamentoException("Não foi encontrado usuario com cpf:" + cpf);
        }
        return convertToDTO(usuario);
    }

    // Buscar usuários por Email
    public List<UsuarioDTO> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Método de conversão de DTO para entidade
    public Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setCpf(usuarioDTO.getCpf());

        if (usuarioDTO.getEndereco() != null) {
            Endereco endereco = enderecoService.convertToEntity(usuarioDTO.getEndereco());
            usuario.setEndereco(endereco);
        }

        if (usuarioDTO.getVeiculosDTO() != null && !usuarioDTO.getVeiculosDTO().isEmpty()) {
            List<Veiculo> veiculos = usuarioDTO.getVeiculosDTO().stream()
                    .map(veiculoService::convertToEntity)
                    .collect(Collectors.toList());
            usuario.setVeiculos(veiculos);
        }

        if (usuarioDTO.getPlanosDTO() != null && !usuarioDTO.getPlanosDTO().isEmpty()) {
            List<Plano> planos = usuarioDTO.getPlanosDTO().stream()
                    .map(planoService::convertToEntity)
                    .collect(Collectors.toList());
            usuario.setPlanos(planos);
        }

        return usuario;
    }

    // Método de conversão de entidade para DTO
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setCpf(usuario.getCpf());


        if (usuario.getVeiculos() != null && !usuario.getVeiculos().isEmpty()) {
            List<VeiculoDTO> veiculosDTO = usuario.getVeiculos().stream()
                    .map(veiculoService::convertToDTO)
                    .collect(Collectors.toList());
            usuarioDTO.setVeiculosDTO(veiculosDTO);
        }

        if (usuario.getPlanos() != null && !usuario.getPlanos().isEmpty()) {
            List<PlanoDTO> planosDTO = usuario.getPlanos().stream()
                    .map(planoService::convertToDTO)
                    .collect(Collectors.toList());
            usuarioDTO.setPlanosDTO(planosDTO);
        }

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
            enderecoService.updateEnderecoFromDTO(usuario.getEndereco(), usuarioDTO.getEndereco());
        }

    }

    // Validação básica do usuário
    private void validarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio");
        }
        if (usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (usuarioDTO.getCpf() == null || !usuarioDTO.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }
}
