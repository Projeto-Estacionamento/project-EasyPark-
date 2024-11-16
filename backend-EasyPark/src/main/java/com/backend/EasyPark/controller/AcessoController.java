package com.backend.EasyPark.controller;

import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acesso")
@CrossOrigin(origins = "http://localhost:3000")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping("/login")
    public ResponseEntity<AcessoDTO> login(@RequestBody AcessoDTO acessoDTO) {
        System.out.println("Tentativa de login: " + acessoDTO.getUsername());
        AcessoDTO usuarioLogado = acessoService.login(acessoDTO);
        return new ResponseEntity<>(usuarioLogado, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarUsuario(@RequestBody AcessoDTO acessoDTO) {
        acessoService.criarUsuario(acessoDTO);
        return new ResponseEntity<>("Usuário criado com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AcessoDTO>> listarUsuarios() {
        List<AcessoDTO> usuarios = acessoService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
