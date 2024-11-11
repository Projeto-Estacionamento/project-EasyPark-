package com.backend.EasyPark.controller;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<FabricanteDTO> criar(@RequestBody FabricanteDTO fabricanteDTO) {
        return ResponseEntity.ok(fabricanteService.criar(fabricanteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fabricanteService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> listarTodos() {
        return ResponseEntity.ok(fabricanteService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteDTO> atualizar(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDTO) {
        return ResponseEntity.ok(fabricanteService.atualizar(id, fabricanteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fabricanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
