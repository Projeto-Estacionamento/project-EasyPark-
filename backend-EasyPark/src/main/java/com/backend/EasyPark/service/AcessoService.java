package com.backend.EasyPark.service;
import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.entities.Acesso;
import com.backend.EasyPark.util.AcessoMapper;
import com.backend.EasyPark.util.validacao.ValidacaoAcesso;
import com.backend.EasyPark.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AcessoService {
    @Autowired
    private AcessoRepository acessoRepository;
    @Autowired
    private AcessoMapper acessoMapper;
    @Autowired
    private ValidacaoAcesso validacaoAcesso;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AcessoDTO login(AcessoDTO acessoDTO) {
        validacaoAcesso.validarAcesso(acessoDTO);
        
        Acesso acesso = acessoRepository.findByUsername(acessoDTO.getUsername());
        if (acesso == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nome de usuário não encontrado");
        }

        if (!passwordEncoder.matches(acessoDTO.getSenha(), acesso.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
        }

        return acessoMapper.toDTO(acesso);
    }

    public void criarUsuario(AcessoDTO acessoDTO) {
        validacaoAcesso.validarAcesso(acessoDTO);
        Acesso acesso = acessoMapper.toEntity(acessoDTO);
        acesso.setSenha(passwordEncoder.encode(acesso.getSenha()));
        acessoRepository.save(acesso);
    }

    public List<AcessoDTO> listarUsuarios() {
        List<Acesso> acessos = acessoRepository.findAll();
        return acessos.stream()
                      .map(acessoMapper::toDTO)
                      .collect(Collectors.toList());
    }
} 