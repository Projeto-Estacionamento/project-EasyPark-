package com.backend.EasyPark.util.validacao;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.UsuarioRepository;
=======
import com.backend.EasyPark.model.dto.UsuarioDTO;
import com.backend.EasyPark.model.entities.Usuario;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.repository.UsuarioRepository;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        if(usuarioExistente.getCpf().equals(dto.getCpf())) {
            throw new EstacionamentoException("CPF JÁ CADASTRADO");
        }
        if (usuarioExistente.getTelefone().equals(dto.getTelefone())) {
            throw new EstacionamentoException("Número de telefone já existe na base de dados");
        }

=======
        if (usuarioExistente != null) {
            throw new EstacionamentoException("CPF JÁ CADASTRADO");
        }
       //Verifique se já existe um usuário com o mesmo telefone
        Usuario usuarioComMesmoTelefone = usuarioRepository.findByTelefone(dto.getTelefone());
        if (usuarioComMesmoTelefone != null) {
            throw new EstacionamentoException("Número de telefone já existe na base de dados");
        }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        if (usuarioDTO.getTelefone() == null || usuarioDTO.getTelefone().trim().isEmpty()) {
=======
        if (usuarioDTO.getTelefone() == null) {
>>>>>>> Stashed changes
            throw new EstacionamentoException("Número de telefone incorreto");
        }
        if (usuarioDTO.getEndereco() == null) {
            throw new EstacionamentoException("Endereço precisa ser informato.");
        }
<<<<<<< Updated upstream


    }



}
=======
    }
}
>>>>>>> Stashed changes
