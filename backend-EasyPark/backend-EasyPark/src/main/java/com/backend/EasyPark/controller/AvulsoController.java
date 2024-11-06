//package com.backend.EasyPark.controller;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.backend.EasyPark.dto.AvulsoDTO;
//import com.backend.EasyPark.service.AvulsoService;
//
//@RestController
//@RequestMapping("/api/avulsos")
//public class AvulsoController {
//
//    @Autowired
//    private AvulsoService avulsoService;
//
//    @PostMapping
//    public ResponseEntity<AvulsoDTO> criarAvulso(@RequestBody AvulsoDTO avulsoDTO) {
//        AvulsoDTO novoAvulso = avulsoService.criarAvulso(avulsoDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(novoAvulso);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AvulsoDTO> buscarAvulsoPorId(@PathVariable Long id) {
//        AvulsoDTO avulso = avulsoService.buscarAvulsoPorId(id);
//        return ResponseEntity.ok(avulso);
//    }
//
//    @GetMapping("/placa/{placaVeiculo}")
//    public ResponseEntity<AvulsoDTO> buscarAvulsoPorPlaca(@PathVariable String placaVeiculo) {
//        AvulsoDTO avulso = avulsoService.buscarAvulsoPorPlaca(placaVeiculo);
//        return ResponseEntity.ok(avulso);
//    }
//
//    @GetMapping("/periodo")
//    public ResponseEntity<List<AvulsoDTO>> buscarAvulsosPorPeriodo(
//            @RequestParam LocalDateTime inicio,
//            @RequestParam LocalDateTime fim) {
//        List<AvulsoDTO> avulsos = avulsoService.buscarAvulsosPorPeriodo(inicio, fim);
//        return ResponseEntity.ok(avulsos);
//    }
//
//    @GetMapping("/em-aberto")
//    public ResponseEntity<List<AvulsoDTO>> buscarAvulsosEmAberto() {
//        List<AvulsoDTO> avulsos = avulsoService.buscarAvulsosEmAberto();
//        return ResponseEntity.ok(avulsos);
//    }
//
//    @GetMapping("/valor-total")
//    public ResponseEntity<List<AvulsoDTO>> buscarAvulsosPorValorTotal(@RequestParam boolean valorTotal) {
//        List<AvulsoDTO> avulsos = avulsoService.buscarAvulsosPorValorTotal(valorTotal);
//        return ResponseEntity.ok(avulsos);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AvulsoDTO> atualizarAvulso(@PathVariable Long id, @RequestBody AvulsoDTO avulsoDTO) {
//        AvulsoDTO avulsoAtualizado = avulsoService.atualizarAvulso(id, avulsoDTO);
//        return ResponseEntity.ok(avulsoAtualizado);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletarAvulso(@PathVariable Long id) {
//        avulsoService.deletarAvulso(id);
//        return ResponseEntity.noContent().build();
//    }
//}
