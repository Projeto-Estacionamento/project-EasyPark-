package com.backend.EasyPark.controller;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.enums.TipoVeiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@Valid @RequestBody PlanoDTO planoDTO) throws EstacionamentoException {
        PlanoDTO novoPlano = planoService.criarPlano(planoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPlanoPorId(@PathVariable Integer id) throws EstacionamentoException {
        PlanoDTO plano = planoService.buscarPlanoPorId(id);
        return ResponseEntity.ok(plano);
    }

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        List<PlanoDTO> planos = planoService.listarPlanos();
        return ResponseEntity.ok(planos);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(@Valid @PathVariable Integer id, @RequestBody PlanoDTO planoDTO) throws EstacionamentoException {
        return ResponseEntity.ok(planoService.atualizarPlano(id, planoDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removerPlano(@PathVariable Integer id) throws EstacionamentoException {
        return ResponseEntity.ok(planoService.deletarPlano(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity <List<PlanoDTO>> buscarPlanoPorTipo(@PathVariable TipoVeiculo tipo) throws EstacionamentoException {
        List<PlanoDTO> planos = planoService.listarPorTipoVeiculo(tipo);
        return ResponseEntity.ok(planos);
    }
}
