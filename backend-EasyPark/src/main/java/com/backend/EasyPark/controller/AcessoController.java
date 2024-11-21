package com.backend.EasyPark.controller;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.dto.AcessoDTO;
import com.backend.EasyPark.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/acesso")
public class AcessoController {


        @Autowired
        private AcessoService acessoService;

        @GetMapping("/by-email")
        public ResponseEntity<AcessoDTO> getUserByEmail(@RequestParam String email) {
            AcessoDTO acessoDTO = acessoService.getByEmail(email);
            return ResponseEntity.ok(acessoDTO);
        }

        @PostMapping
        public ResponseEntity<AcessoDTO> save(@RequestBody AcessoDTO userDTO) {
            AcessoDTO acessoDTO = acessoService.save(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(acessoDTO);
        }

        @GetMapping
        public ResponseEntity<List<AcessoDTO>> findAll() throws EstacionamentoException {
            List<AcessoDTO> acessoDTOS = acessoService.findAll();

            return ResponseEntity.ok(acessoDTOS);
        }

        @GetMapping("/{id}")
        public ResponseEntity<AcessoDTO> findById(@PathVariable Integer id) throws EstacionamentoException {
            AcessoDTO acessoDTO = acessoService.findById(id);
            return ResponseEntity.ok(acessoDTO);
        }

        @PutMapping
        public ResponseEntity<AcessoDTO> update(@RequestBody AcessoDTO userDTO) {
            AcessoDTO acessoDtoUpdate = acessoService.update(userDTO);
            return ResponseEntity.ok(acessoDtoUpdate);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
            acessoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

}
