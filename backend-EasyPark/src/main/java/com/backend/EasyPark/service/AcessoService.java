package com.backend.EasyPark.service;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.entities.Acesso;
import com.backend.EasyPark.util.AcessoMapper;
import com.backend.EasyPark.util.validacao.ValidacaoAcesso;
import com.backend.EasyPark.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private AcessoMapper acessoMapper;

    @Autowired
    private ValidacaoAcesso validacaoAcesso;

    public AcessoDTO login(AcessoDTO acessoDTO) {
        validacaoAcesso.validarAcesso(acessoDTO);
        
        Acesso acesso = acessoRepository.findByUsername(acessoDTO.getUsername());
        if (acesso == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        if (!acesso.getSenha().equals(acessoDTO.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        return acessoMapper.toDTO(acesso);
    }
} 
=======
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.dto.AcessoDTO;
import com.backend.EasyPark.model.entities.Acesso;
import com.backend.EasyPark.model.repository.AcessoRepository;
import com.backend.EasyPark.util.AcessoMapper;
import com.backend.EasyPark.util.validacao.ValidacaoAcesso;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.backend.EasyPark.model.enums.TipoAcesso;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcessoService implements UserDetailsService {

    @Autowired
    private AcessoRepository repository;

    public AcessoDTO save(AcessoDTO userDTO) {

        if (!ValidacaoAcesso.isNotEmpty(userDTO.getEmail())) {
            throw new IllegalArgumentException("O e-mail não pode estar nulo!");
        }

        if (!ValidacaoAcesso.isNotEmpty(userDTO.getSenha())) {
            throw new IllegalArgumentException("A senha não pode estar nula!");
        }

        if (!ValidacaoAcesso.isValidLength(userDTO.getSenha(), 8)) {
            throw new IllegalArgumentException("A senha senha não pode conter menos que 8 caracteres!");
        }

        if (repository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Este e-mail já está cadastrado");
        }

        Acesso user = AcessoMapper.toEntity(userDTO);

        Acesso userSalvo = repository.save(user);

        AcessoDTO salvarAcesso = AcessoMapper.toDTO(userSalvo);

        return salvarAcesso;
    }

    public List<AcessoDTO> findAll() throws EstacionamentoException {
        List<Acesso> users = repository.findAll();

        List<AcessoDTO> usersDTO = users.stream()
                .map(AcessoMapper::toDTO)
                .collect(Collectors.toList());

        if (usersDTO.isEmpty()) {
            throw new EstacionamentoException("Nenhum usuário encontrado.");
        }

        return usersDTO;
    }

    public AcessoDTO findById(Integer id) throws EstacionamentoException {
        Optional<Acesso> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            Acesso user = userOptional.get();
            return AcessoMapper.toDTO(user);
        } else {
            throw new EstacionamentoException("Usuário com ID " + id + " não encontrado!");
        }
    }

    // quebrado pq da erro de constraint
    public void deleteById(Integer id) {
        Optional<Acesso> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
        }
    }

    public AcessoDTO update(AcessoDTO userDTO) {
        Optional<Acesso> userOptional = repository.findById(userDTO.getId());

        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("Usuário com ID " + userDTO.getId() + " não encontrado");
        }

        Acesso userSalvo = userOptional.get();

        // Mapeando o DTO para a entidade User
        Acesso user = AcessoMapper.toEntity(userDTO);

        // Copiando as propriedades do objeto User atualizado, exceto o "id"
        BeanUtils.copyProperties(user, userSalvo, "id");

        // Salvando o usuário atualizado no banco
        userSalvo = repository.save(userSalvo);

        // Retornando o DTO do usuário atualizado
        return AcessoMapper.toDTO(userSalvo);
    }

    public AcessoDTO getByEmail(String email) {
        Optional<Acesso> userOptional = repository.findByEmail(email);

        if (userOptional.isPresent()) {
            return AcessoMapper.toDTO(userOptional.get());
        } else {
            throw new EntityNotFoundException("Usuário com o email " + email + " não encontrado!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuário não encontrado" + username)
                );
    }

    public List<AcessoDTO> findByTipoAcesso(TipoAcesso tipo) {
        List<Acesso> acessos = repository.findByTipoAcesso(tipo);
        return acessos.stream()
                .map(AcessoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
>>>>>>> Stashed changes
