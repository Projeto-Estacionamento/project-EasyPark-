package com.backend.EasyPark.controller;

import java.util.List;

<<<<<<< Updated upstream
import com.backend.EasyPark.exception.EstacionamentoException;
=======
import com.backend.EasyPark.auth.AuthenticationService;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.entities.Acesso;
import com.backend.EasyPark.model.enums.TipoAcesso;
import com.backend.EasyPark.model.seletor.UsuarioSeletor;
import com.backend.EasyPark.util.validacao.ValidarTipoAcesso;
>>>>>>> Stashed changes
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EasyPark.dto.UsuarioDTO;
=======
import org.springframework.web.bind.annotation.*;

import com.backend.EasyPark.model.dto.UsuarioDTO;
>>>>>>> Stashed changes
import com.backend.EasyPark.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

<<<<<<< Updated upstream
   @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws EstacionamentoException {
=======
    @Autowired
    private ValidarTipoAcesso validarTipoAcesso;

   @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Integer id) throws EstacionamentoException {
<<<<<<< Updated upstream
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
        System.out.println("VeiculosDTO: " + usuario.getVeiculosDTO());
=======
        validarTipoAcesso.validarSeExisteUsuario();
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
>>>>>>> Stashed changes
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
<<<<<<< Updated upstream
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
=======
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() throws EstacionamentoException {
        validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

<<<<<<< Updated upstream
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) throws EstacionamentoException {
=======
    @PutMapping("/{id}/update")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@Valid @PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) throws EstacionamentoException {
        validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        UsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) throws EstacionamentoException {
<<<<<<< Updated upstream
=======
       validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorCpf(@Valid @PathVariable String cpf) throws EstacionamentoException {
<<<<<<< Updated upstream
=======
        validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        UsuarioDTO usuarios = usuarioService.buscarUsuarioPorCpf(cpf);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorEmail(@PathVariable String email) throws EstacionamentoException {
<<<<<<< Updated upstream
=======
        validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(usuarios);
    }

<<<<<<< Updated upstream
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
=======

    @PostMapping("/filtro")
    public List<UsuarioDTO> pesquisarComSeletor(@RequestBody UsuarioSeletor seletor) throws EstacionamentoException {
        validarTipoAcesso.validarSeExisteUsuario();
        return usuarioService.pesquisarComSeletor(seletor);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

>>>>>>> Stashed changes
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
<<<<<<< Updated upstream
}
=======


}
>>>>>>> Stashed changes
