package com.backend.EasyPark.service;

import java.util.ArrayList;
import java.util.List;
<<<<<<< Updated upstream
import java.util.stream.Collectors;

import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.util.EnderecoMapper;
import com.backend.EasyPark.util.PlanoMapper;
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
=======
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.repository.PlanoRepository;
import com.backend.EasyPark.model.seletor.UsuarioSeletor;
import com.backend.EasyPark.util.EnderecoMapper;
import com.backend.EasyPark.util.UsuarioMapper;
import com.backend.EasyPark.util.validacao.ValidacaoUsuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.EasyPark.model.dto.UsuarioDTO;
import com.backend.EasyPark.model.entities.Usuario;
import com.backend.EasyPark.model.entities.Endereco;
import com.backend.EasyPark.model.repository.UsuarioRepository;
>>>>>>> Stashed changes

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ValidacaoUsuario validacaoUsuario;

<<<<<<< Updated upstream
=======
    @Autowired
    private PlanoRepository planoRepository;

>>>>>>> Stashed changes

    public UsuarioService(ValidacaoUsuario validacaoUsuario) {
        this.validacaoUsuario = validacaoUsuario;
    }

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    // Criar um novo usuário
    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validacaoUsuario.validarCamposUsuario(usuarioDTO);
        validacaoUsuario.validarSeExisteCampoNoBancoDados(usuarioDTO);
<<<<<<< Updated upstream
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
=======
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(savedUsuario);
>>>>>>> Stashed changes
    }

    // Buscar um usuário pelo ID
    public UsuarioDTO buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
<<<<<<< Updated upstream
                .map(UsuarioService::convertToDTO)
=======
                .map(UsuarioMapper::toDTO)
>>>>>>> Stashed changes
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Listar todos os usuários
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
<<<<<<< Updated upstream
                .map(UsuarioService::convertToDTO)
=======
                .map(UsuarioMapper::toDTO)
>>>>>>> Stashed changes
                .collect(Collectors.toList());
    }

    // Atualizar um usuário
<<<<<<< Updated upstream
    @Transactional
    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validacaoUsuario.validarCamposUsuario(usuarioDTO);
        validacaoUsuario.validarSeExisteCampoNoBancoDados(usuarioDTO);
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    updateUsuarioFromDTO(usuario, usuarioDTO);
                    return convertToDTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deletar um usuário
    @Transactional
=======

    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validacaoUsuario.validarCamposUsuario(usuarioDTO);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (!usuarioOpt.isPresent()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Copia as propriedades do DTO para o usuário existente, exceto o id
        BeanUtils.copyProperties(usuarioDTO, usuario, "id");
        // Atualiza o Endereco, se presente no DTO
        if (usuarioDTO.getEndereco() != null) {
            Endereco enderecoExistente = usuario.getEndereco();
            if (enderecoExistente != null && enderecoExistente.getId() != null) {
                //Atualiza as propriedades do Endereco sem modificar o id
                enderecoExistente.setCep(usuarioDTO.getEndereco().getCep());
                enderecoExistente.setCidade(usuarioDTO.getEndereco().getCidade());
                enderecoExistente.setEstado(usuarioDTO.getEndereco().getEstado());
            } else {
                //Caso o Endereco não exista ou o id seja nulo, cria um novo
                usuario.setEndereco(EnderecoMapper.toEntity(usuarioDTO.getEndereco()));
            }
        }

        // Salva o usuário atualizado
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    // Deletar um usuário
>>>>>>> Stashed changes
    public void deletarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    // Buscar usuários por CPF
    public UsuarioDTO buscarUsuarioPorCpf(String cpf) throws EstacionamentoException {
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if (usuario == null) {
            throw new EstacionamentoException("Não foi encontrado usuario com cpf:" + cpf);
        }
<<<<<<< Updated upstream
        return convertToDTO(usuario);
=======
        return UsuarioMapper.toDTO(usuario);
>>>>>>> Stashed changes
    }

    //Buscar usuários por Email
    public List<UsuarioDTO> buscarUsuarioPorEmail(String email) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(email);
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
<<<<<<< Updated upstream
            UsuarioDTO usuarioDTO = convertToDTO(usuario);
=======
            UsuarioDTO usuarioDTO = UsuarioMapper.toDTO(usuario);
>>>>>>> Stashed changes
            usuariosDTO.add(usuarioDTO);
        }

        return usuariosDTO;
    }
<<<<<<< Updated upstream

    //Método de conversão de DTO para entidade
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

    }
}
=======
    public List<UsuarioDTO> pesquisarComSeletor(UsuarioSeletor seletor) {
        if(seletor != null && seletor.temPaginacao()) {
            int pageNumber = seletor.getPagina();
            int pageSize = seletor.getLimite();

            //Ler com atenção a documentação:
            // @param pageNumber zero-based page number, must not be negative.
            // @param pageSize the size of the page to be returned, must be greater than 0.
            PageRequest pagina = PageRequest.of(pageNumber - 1, pageSize);
            return UsuarioMapper.toDTOList(usuarioRepository.findAll(seletor, pagina).toList());
        }

        //https://www.baeldung.com/spring-data-jpa-query-by-example
        return UsuarioMapper.toDTOList(usuarioRepository.findAll(seletor));
    }


}
>>>>>>> Stashed changes
