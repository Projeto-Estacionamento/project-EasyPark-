package com.backend.EasyPark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EasyPark.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.service.ConfiguracaoSistemaService;

@RestController
@RequestMapping("/api/configuracoes")
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
    public ResponseEntity<ConfiguracaoSistemaDTO> atualizarConfiguracao(@PathVariable Long id, @RequestBody ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistemaDTO configuracaoAtualizada = configuracaoSistemaService.atualizarConfiguracao(id, configuracaoDTO);
        return ResponseEntity.ok(configuracaoAtualizada);
    }

    @GetMapping
    public ResponseEntity<List<ConfiguracaoSistemaDTO>> listarTodasConfiguracoes() {
        List<ConfiguracaoSistemaDTO> configuracoes = configuracaoSistemaService.listarTodasConfiguracoes();
        return ResponseEntity.ok(configuracoes);
    }
}
