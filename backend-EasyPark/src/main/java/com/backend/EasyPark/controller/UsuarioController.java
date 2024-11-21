package com.backend.EasyPark.controller;

import java.util.List;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.seletor.UsuarioSeletor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.EasyPark.model.dto.UsuarioDTO;
import com.backend.EasyPark.service.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

   @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws EstacionamentoException {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Integer id) throws EstacionamentoException {
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
        System.out.println("VeiculosDTO: " + usuario.getVeiculosDTO());
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@Valid @PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) throws EstacionamentoException {
        UsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) throws EstacionamentoException {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorCpf(@Valid @PathVariable String cpf) throws EstacionamentoException {
        UsuarioDTO usuarios = usuarioService.buscarUsuarioPorCpf(cpf);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorEmail(@PathVariable String email) throws EstacionamentoException {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(usuarios);
    }


    @PostMapping("/filtro")
    public List<UsuarioDTO> pesquisarComSeletor(@RequestBody UsuarioSeletor seletor) {
        return usuarioService.pesquisarComSeletor(seletor);
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