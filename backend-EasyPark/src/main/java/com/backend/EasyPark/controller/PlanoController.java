package com.backend.EasyPark.controller;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.service.PlanoService;
=======
import com.backend.EasyPark.model.dto.PlanoDTO;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.service.PlanoService;
import com.backend.EasyPark.util.validacao.ValidarTipoAcesso;
import jakarta.validation.Valid;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO planoDTO) throws EstacionamentoException {
=======
    @Autowired
    private ValidarTipoAcesso validarTipoAcesso;

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@Valid @RequestBody PlanoDTO planoDTO) throws EstacionamentoException {
        validarTipoAcesso.validarAcessoAdmin();
>>>>>>> Stashed changes
        PlanoDTO novoPlano = planoService.criarPlano(planoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPlanoPorId(@PathVariable Integer id) throws EstacionamentoException {
<<<<<<< Updated upstream
=======
        validarTipoAcesso.validarSeExisteUsuario();
>>>>>>> Stashed changes
        PlanoDTO plano = planoService.buscarPlanoPorId(id);
        return ResponseEntity.ok(plano);
    }

    @GetMapping
<<<<<<< Updated upstream
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        List<PlanoDTO> planos = planoService.listarPlanos();
        return ResponseEntity.ok(planos);
    }
}
=======
    public ResponseEntity<List<PlanoDTO>> listarPlanos() throws EstacionamentoException {
        validarTipoAcesso.validarSeExisteUsuario();
        List<PlanoDTO> planos = planoService.listarPlanos();
        return ResponseEntity.ok(planos);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(@Valid @PathVariable Integer id, @RequestBody PlanoDTO planoDTO) throws EstacionamentoException {
        validarTipoAcesso.validarAcessoAdmin();
        return ResponseEntity.ok(planoService.atualizarPlano(id, planoDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removerPlano(@PathVariable Integer id) throws EstacionamentoException {
        validarTipoAcesso.validarAcessoAdmin();
        return ResponseEntity.ok(planoService.deletarPlano(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity <List<PlanoDTO>> buscarPlanoPorTipo(@PathVariable TipoVeiculo tipo) throws EstacionamentoException {
        validarTipoAcesso.validarSeExisteUsuario();
        List<PlanoDTO> planos = planoService.listarPorTipoVeiculo(tipo);
        return ResponseEntity.ok(planos);
    }
}
>>>>>>> Stashed changes
