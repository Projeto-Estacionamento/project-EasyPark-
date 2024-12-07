package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.entities.Fabricante;
import com.backend.EasyPark.repository.FabricanteRepository;
import com.backend.EasyPark.util.FabricanteMapper;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    public FabricanteDTO criar(FabricanteDTO fabricanteDTO) {
        validarAno(fabricanteDTO.getAno());
        Fabricante fabricante = FabricanteMapper.toEntity(fabricanteDTO);
        return FabricanteMapper.toDTO(fabricanteRepository.save(fabricante));
    }

    public FabricanteDTO buscarPorId(Integer id) {
        return fabricanteRepository.findById(id)
                .map(FabricanteMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
    }

    public List<FabricanteDTO> listarTodos() {
        return fabricanteRepository.findAll().stream()
                .map(FabricanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FabricanteDTO atualizar(Integer id, FabricanteDTO fabricanteDTO) {
        validarAno(fabricanteDTO.getAno());
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        FabricanteMapper.updateEntityFromDTO(fabricante, fabricanteDTO);
        return FabricanteMapper.toDTO(fabricanteRepository.save(fabricante));
    }

    public void deletar(Integer id) {
        fabricanteRepository.deleteById(id);
    }

    private void validarAno(int ano) {
        if (ano < 1886) {
            throw new IllegalArgumentException("O ano do fabricante deve ser a partir de 1886");
        }
    }
}
