package com.backend.EasyPark.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoDTO> criarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        // Lógica para criar um novo veículo
        VeiculoDTO novoVeiculo = veiculoService.criarVeiculo(veiculoDTO);
        return ResponseEntity.ok(novoVeiculo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorId(@PathVariable Long id) {
        // Lógica para buscar um veículo por ID
        VeiculoDTO veiculo = veiculoService.buscarVeiculoPorId(id);
        if (veiculo != null) {
            return ResponseEntity.ok(veiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listarVeiculos() {
        // Lógica para listar todos os veículos
        // List<VeiculoDTO> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO) {
        // Lógica para atualizar um veículo
        VeiculoDTO veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculoDTO);
        if (veiculoAtualizado != null) {
            return ResponseEntity.ok(veiculoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/ocupados")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculosOcupandoVaga() {
        // Lógica para listar veículos ocupando vaga
        List<VeiculoDTO> veiculosOcupados = veiculoService.listarVeiculosOcupandoVaga();
        return ResponseEntity.ok(veiculosOcupados);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
