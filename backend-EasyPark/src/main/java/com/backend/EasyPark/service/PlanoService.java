package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;


import com.backend.EasyPark.util.PlanoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Plano;

import com.backend.EasyPark.repository.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;



    // Criar um novo plano
    @Transactional
    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        // Validação dos campos
        validarPlano(planoDTO);
        // Conversão para entidade
        Plano plano = PlanoMapper.convertToEntity(planoDTO);
        // Salva no banco de dados
        Plano planoSalvo = planoRepository.save(plano);
        // Converte de volta para DTO e retorna
        return PlanoMapper.convertToDTO(planoSalvo);
    }

    // Buscar plano pelo ID
    public PlanoDTO buscarPlanoPorId(Integer id) {
        return planoRepository.findById(id)
                .map(PlanoMapper::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    // Listar todos os planos
    public List<PlanoDTO> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(PlanoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    // Atualizar um plano
    @Transactional
    public PlanoDTO atualizarPlano(Integer id, PlanoDTO planoDTO) {
        validarPlano(planoDTO);
        return planoRepository.findById(id)
                .map(plano -> {
                    PlanoMapper.updatePlanoFromDTO(plano, planoDTO);
                    return PlanoMapper.convertToDTO(planoRepository.save(plano));
                })
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    // Deletar um plano
    @Transactional
    public void deletarPlano(Integer id) {
        planoRepository.deleteById(id);
    }

    // Validação básica de plano
    private void validarPlano(PlanoDTO planoDTO) {
        if (planoDTO.getTipoPlano() == null) {
            throw new IllegalArgumentException("Tipo de plano não pode ser nulo");
        }
        if (planoDTO.getTipoVeiculo() == null) {
            throw new IllegalArgumentException("Tipo de veículo não pode ser nulo");
        }
        if (planoDTO.getValorPlano() <= 0) {
            throw new IllegalArgumentException("Valor do plano deve ser maior que 0");
        }

    }

}
