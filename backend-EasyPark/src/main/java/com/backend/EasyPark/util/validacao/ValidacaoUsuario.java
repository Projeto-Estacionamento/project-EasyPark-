package com.backend.EasyPark.util.validacao;

import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.UsuarioRepository;
import com.backend.EasyPark.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class ValidacaoUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    public void validarSeExisteCampoNoBancoDados(UsuarioDTO dto) throws EstacionamentoException {
        Usuario usuarioExistente = usuarioRepository.findByCpf(dto.getCpf());

        if(usuarioExistente.getCpf().equals(dto.getCpf())) {
            throw new EstacionamentoException("CPF JÁ CADASTRADO");
        }
        if (usuarioExistente.getTelefone().equals(dto.getTelefone())) {
            throw new EstacionamentoException("Número de telefone já existe na base de dados");
        }

    }

    public void validarCamposUsuario(UsuarioDTO usuarioDTO) throws EstacionamentoException {
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().trim().isEmpty()) {
            throw new EstacionamentoException("Nome do usuário não pode ser vazio");
        }
        if (usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new EstacionamentoException("Email inválido");
        }
        if (usuarioDTO.getCpf() == null) {
            throw new EstacionamentoException("CPF inválido");
        }
        if (usuarioDTO.getTelefone() == null || usuarioDTO.getTelefone().trim().isEmpty()) {
            throw new EstacionamentoException("Número de telefone incorreto");
        }
        if (usuarioDTO.getEndereco() == null) {
            throw new EstacionamentoException("Endereço precisa ser informato.");
        }
    }
}
