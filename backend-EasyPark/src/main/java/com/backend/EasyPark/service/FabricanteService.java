package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.entities.Fabricante;
import com.backend.EasyPark.repository.FabricanteRepository;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    public FabricanteDTO criar(FabricanteDTO fabricanteDTO) {
        validarAno(fabricanteDTO.getAno());
        Fabricante fabricante = new Fabricante();
        fabricante.setModelo(fabricanteDTO.getModelo());
        fabricante.setMarca(fabricanteDTO.getMarca());
        fabricante.setAno(fabricanteDTO.getAno());
        return convertToDTO(fabricanteRepository.save(fabricante));
    }

    public FabricanteDTO buscarPorId(Long id) {
        return fabricanteRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
    }

    public List<FabricanteDTO> listarTodos() {
        return fabricanteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FabricanteDTO atualizar(Long id, FabricanteDTO fabricanteDTO) {
        validarAno(fabricanteDTO.getAno());
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        fabricante.setModelo(fabricanteDTO.getModelo());
        fabricante.setMarca(fabricanteDTO.getMarca());
        fabricante.setAno(fabricanteDTO.getAno());
        return convertToDTO(fabricanteRepository.save(fabricante));
    }

    public void deletar(Long id) {
        fabricanteRepository.deleteById(id);
    }

    private FabricanteDTO convertToDTO(Fabricante fabricante) {
        return new FabricanteDTO(
                fabricante.getId(),
                fabricante.getModelo(),
                fabricante.getMarca(),
                fabricante.getAno()
        );
    }

    private void validarAno(int ano) {
        // Botei 1886 porque 
        if (ano < 1886) {
            throw new IllegalArgumentException("O ano do fabricante deve ser a partir de 1886");
        }
    }
}
