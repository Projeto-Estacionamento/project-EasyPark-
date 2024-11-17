package com.backend.EasyPark.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.backend.EasyPark.entities.AssinaturaPlano;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.AssinaturaPlanoRepository;
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
    @Autowired
    private AssinaturaPlanoRepository assinaturaPlanoRepository;


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
    public boolean deletarPlano(Integer id) throws EstacionamentoException {
        boolean retorno = false;
        Optional<List<AssinaturaPlano>> assinantes = assinaturaPlanoRepository.findByPlanoId(id);

        Plano plano = planoRepository.findById(id).orElseThrow(() -> new EstacionamentoException("Plano nao encontrado"));

        if (assinantes.get().isEmpty()) {
            planoRepository.deleteById(id);
            retorno = true;
        } else{
            throw new EstacionamentoException("Nao pode excluir um plano, pois tem assinantes associado.");
        }

        return retorno;
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
