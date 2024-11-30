package com.backend.EasyPark.service;

import com.backend.EasyPark.model.dto.RelatorioTicketsFechadosDTO;
import com.backend.EasyPark.model.entities.RelatorioTicketsFechados;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import com.backend.EasyPark.model.repository.RelatorioTicketsFechadosRepository;
import com.backend.EasyPark.util.RelatorioTicketsFechadosMapper; // Certifique-se de ter o mapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioTicketsFechadosService {

    private final RelatorioTicketsFechadosRepository relatorioRepository;

    @Autowired
    public RelatorioTicketsFechadosService(RelatorioTicketsFechadosRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public List<RelatorioTicketsFechadosDTO> listarRelatorios() {
        return relatorioRepository.findAll().stream()
                .map(RelatorioTicketsFechadosMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RelatorioTicketsFechadosDTO buscarRelatorioPorId(Integer id) {
        RelatorioTicketsFechados relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
        return RelatorioTicketsFechadosMapper.toDTO(relatorio);
    }

    public List<RelatorioTicketsFechadosDTO> buscarPorTipoVeiculo(TipoVeiculo tipoVeiculo){
        return relatorioRepository.findByTipoVeiculo(tipoVeiculo).stream()
                .map(RelatorioTicketsFechadosMapper::toDTO)
                .collect(Collectors.toList());
    }
}