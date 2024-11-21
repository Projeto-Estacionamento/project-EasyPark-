package com.backend.EasyPark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.EasyPark.model.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.service.ConfiguracaoSistemaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/configuracoes")
public class ConfiguracaoSistemaController {

    @Autowired
    private ConfiguracaoSistemaService configuracaoSistemaService;

    @PostMapping
    public ResponseEntity<ConfiguracaoSistemaDTO> criarConfiguracao(@RequestBody ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistemaDTO novaConfiguracao = configuracaoSistemaService.criarConfiguracao(configuracaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConfiguracao);
    }

    @GetMapping("/atual")
    public ResponseEntity<ConfiguracaoSistemaDTO> buscarConfiguracaoAtual() {
        ConfiguracaoSistemaDTO configuracao = configuracaoSistemaService.buscarConfiguracaoAtual();
        return ResponseEntity.ok(configuracao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracaoSistemaDTO> atualizarConfiguracao(@PathVariable Integer id, @RequestBody ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistemaDTO configuracaoAtualizada = configuracaoSistemaService.atualizarConfiguracao(id, configuracaoDTO);
        return ResponseEntity.ok(configuracaoAtualizada);
    }

    @GetMapping
    public ResponseEntity<List<ConfiguracaoSistemaDTO>> listarTodasConfiguracoes() {
        List<ConfiguracaoSistemaDTO> configuracoes = configuracaoSistemaService.listarTodasConfiguracoes();
        return ResponseEntity.ok(configuracoes);
    }
}
