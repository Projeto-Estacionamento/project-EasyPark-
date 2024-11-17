package com.backend.EasyPark.controller;

import com.backend.EasyPark.dto.AssinaturaPlanoDTO;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.service.AssinaturaPlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@RequiredArgsConstructor
public class AssinaturaPlanoController {

    private final AssinaturaPlanoService assinaturaPlanoService;

    @GetMapping
    public ResponseEntity<List<AssinaturaPlanoDTO>> findAll() {
        return ResponseEntity.ok(assinaturaPlanoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaPlanoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(assinaturaPlanoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AssinaturaPlanoDTO> create(@RequestBody AssinaturaPlanoDTO dto) throws EstacionamentoException {
        AssinaturaPlanoDTO created = assinaturaPlanoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssinaturaPlanoDTO> update(@PathVariable Integer id, @RequestBody AssinaturaPlanoDTO dto) {
        return ResponseEntity.ok(assinaturaPlanoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws EstacionamentoException {
        assinaturaPlanoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}