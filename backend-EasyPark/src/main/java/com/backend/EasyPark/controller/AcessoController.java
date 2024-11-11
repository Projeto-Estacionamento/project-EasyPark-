package com.backend.EasyPark.controller;

import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acesso")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping("/login")
    public ResponseEntity<AcessoDTO> login(@RequestBody AcessoDTO acessoDTO) {
        AcessoDTO usuarioLogado = acessoService.login(acessoDTO);
        return new ResponseEntity<>(usuarioLogado, HttpStatus.OK);
    }
}
