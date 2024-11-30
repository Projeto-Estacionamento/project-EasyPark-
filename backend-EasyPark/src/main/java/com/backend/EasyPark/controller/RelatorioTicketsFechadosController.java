package com.backend.EasyPark.controller;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.dto.RelatorioTicketsFechadosDTO;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import com.backend.EasyPark.service.RelatorioTicketsFechadosService; // Você precisará criar este serviço
import com.backend.EasyPark.util.validacao.ValidarTipoAcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioTicketsFechadosController {

    private final ValidarTipoAcesso validarTipoAcesso;
    private final RelatorioTicketsFechadosService relatorioService;

    @Autowired
    public RelatorioTicketsFechadosController(ValidarTipoAcesso validarTipoAcesso, RelatorioTicketsFechadosService relatorioService) {
        this.validarTipoAcesso = validarTipoAcesso;
        this.relatorioService = relatorioService;
    }

    //Endpoint
    @GetMapping("/tickets-fechados")
    public ResponseEntity<List<RelatorioTicketsFechadosDTO>> listarRelatorios() throws EstacionamentoException {
        validarTipoAcesso.validarAcessoAdmin();
        return ResponseEntity.ok(relatorioService.listarRelatorios()); // Você precisará implementar este método no serviço
    }

    //Endpoint
    @GetMapping("/tickets-fechados/{id}")
    public ResponseEntity<RelatorioTicketsFechadosDTO> buscarRelatorioPorId(@PathVariable Integer id) throws EstacionamentoException {
        validarTipoAcesso.validarAcessoAdmin();
        return ResponseEntity.ok(relatorioService.buscarRelatorioPorId(id)); // Você precisará implementar este método no serviço
    }

    @GetMapping("/tickets-fchado-tipo/{tipoVeiculo}")
    public ResponseEntity <List<RelatorioTicketsFechadosDTO>> listarRelatorioTipo(@PathVariable TipoVeiculo tipoVeiculo) throws EstacionamentoException {
        validarTipoAcesso.validarAcessoAdmin();
        return ResponseEntity.ok(relatorioService.buscarPorTipoVeiculo(tipoVeiculo));
    }
}