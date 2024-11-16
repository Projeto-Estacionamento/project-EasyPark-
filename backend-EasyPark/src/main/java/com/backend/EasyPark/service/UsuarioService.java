package com.backend.EasyPark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.PlanoRepository;
import com.backend.EasyPark.util.EnderecoMapper;
import com.backend.EasyPark.util.PlanoMapper;
import com.backend.EasyPark.util.UsuarioMapper;
import com.backend.EasyPark.util.VeiculoMapper;
import com.backend.EasyPark.util.validacao.ValidacaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Endereco;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ValidacaoUsuario validacaoUsuario;

    @Autowired
    private PlanoRepository planoRepository;


    public UsuarioService(ValidacaoUsuario validacaoUsuario) {
        this.validacaoUsuario = validacaoUsuario;
    }


    // Criar um novo usuário
    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validacaoUsuario.validarCamposUsuario(usuarioDTO);
        validacaoUsuario.validarSeExisteCampoNoBancoDados(usuarioDTO);
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(savedUsuario);
    }

    // Buscar um usuário pelo ID
    public UsuarioDTO buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Listar todos os usuários
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Atualizar um usuário
    @Transactional
    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validacaoUsuario.validarCamposUsuario(usuarioDTO);
        validacaoUsuario.validarSeExisteCampoNoBancoDados(usuarioDTO);
        return usuarioRepository.findById(id)
                .map(usuario -> {
                   UsuarioMapper.updateUsuarioFromDTO(usuario, usuarioDTO);
                    return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deletar um usuário
    @Transactional
    public void deletarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

  /*  public UsuarioDTO associarPlanoAoUsuario(Integer usuarioId, Integer planoId) throws EstacionamentoException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EstacionamentoException("Usuário não encontrado"));

        Plano plano = planoRepository.findById(planoId)
                .orElseThrow(() -> new EstacionamentoException("Plano não encontrado"));

        usuario.setPlano(plano); //ERRO AQUI
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return UsuarioMapper.toDTO(usuarioAtualizado);
    }*/

    // Buscar usuários por CPF
    public UsuarioDTO buscarUsuarioPorCpf(String cpf) throws EstacionamentoException {
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if (usuario == null) {
            throw new EstacionamentoException("Não foi encontrado usuario com cpf:" + cpf);
        }
        return UsuarioMapper.toDTO(usuario);
    }

    //Buscar usuários por Email
    public List<UsuarioDTO> buscarUsuarioPorEmail(String email) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(email);
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = UsuarioMapper.toDTO(usuario);
            usuariosDTO.add(usuarioDTO);
        }

        return usuariosDTO;
    }


}
