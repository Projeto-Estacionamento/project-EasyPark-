package com.backend.EasyPark.controller;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.service.PlanoService;
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
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO planoDTO) throws EstacionamentoException {
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
}