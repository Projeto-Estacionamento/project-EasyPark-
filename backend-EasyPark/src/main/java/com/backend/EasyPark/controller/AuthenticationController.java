package com.backend.EasyPark.controller;

import com.backend.EasyPark.auth.AuthenticationService;
import com.backend.EasyPark.auth.JwtService;
import com.backend.EasyPark.model.dto.LoginDTO;
import com.backend.EasyPark.model.dto.RegisterDTO;
import com.backend.EasyPark.model.entities.Acesso;
import com.backend.EasyPark.model.enums.TipoAcesso;
import com.backend.EasyPark.model.repository.AcessoRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private AuthenticationManager authenticationManager;


    @Autowired
    private AcessoRepository repository;


    @Autowired
    private JwtService jwtService;

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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO data) {
        // Cria um objeto de autenticação com o login e a senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());

        // Autentica o usuário usando o AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        // Gera o token JWT
        var token = jwtService.getGenerateToken(authentication);

        // Retorna o token na resposta
        return ResponseEntity.ok(token);
    }



    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (repository.existsByEmail(registerDTO.email())) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        String encryptedPassword = passwordEncoder.encode(registerDTO.senha());
        Acesso novoAcesso = new Acesso(registerDTO.nome(), encryptedPassword, registerDTO.email(), registerDTO.tipoAcesso());

        repository.save(novoAcesso);

        return ResponseEntity.ok().build();
    }

   /* private String username;

    @Size(min = 0, max = 999)
    private String senha;

    private String email;
    @Enumerated(EnumType.STRING)
    private TipoAcesso tipoAcesso;*/

}