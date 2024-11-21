package com.backend.EasyPark.controller;

import com.backend.EasyPark.auth.AuthenticationService;
import com.backend.EasyPark.model.entities.Acesso;
import com.backend.EasyPark.model.enums.TipoAcesso;
import com.backend.EasyPark.service.AcessoService;
import com.backend.EasyPark.util.AcessoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AcessoService acessoService;

    /**
     * Método de login padronizado -> Basic Auth
     *
     *  O parâmetro Authentication já encapsula login (username) e senha (password)
     *  Basic <Base64 encoded username and password>
     * @param authentication
     * @return o JWT gerado
     */
    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }

    @PostMapping("/novo-acesso")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registrarJogador(@RequestBody Acesso novoAcesso) {

        String senhaCifrada = passwordEncoder.encode(novoAcesso.getSenha());

        novoAcesso.setSenha(senhaCifrada);
        novoAcesso.setTipoAcesso(TipoAcesso.CAIXA);

       acessoService.save(AcessoMapper.toDTO(novoAcesso));
    }

}