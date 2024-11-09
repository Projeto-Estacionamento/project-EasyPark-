package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;


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
    @Lazy
    private UsuarioService usuarioService;


    // Criar um novo plano
    @Transactional
    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        validarPlano(planoDTO);
        Plano plano = convertToEntity(planoDTO);
        Plano savedPlano = planoRepository.save(plano);
        return convertToDTO(savedPlano);
    }

    // Buscar plano pelo ID
    public PlanoDTO buscarPlanoPorId(Integer id) {
        return planoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    // Listar todos os planos
    public List<PlanoDTO> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Atualizar um plano
    @Transactional
    public PlanoDTO atualizarPlano(Integer id, PlanoDTO planoDTO) {
        validarPlano(planoDTO);
        return planoRepository.findById(id)
                .map(plano -> {
                    updatePlanoFromDTO(plano, planoDTO);
                    return convertToDTO(planoRepository.save(plano));
                })
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    // Deletar um plano
    @Transactional
    public void deletarPlano(Integer id) {
        planoRepository.deleteById(id);
    }

    // Método de conversão de DTO para entidade
    Plano convertToEntity(PlanoDTO planoDTO) {
        Plano plano = new Plano();
        plano.setId(planoDTO.getId());
        plano.setTipoPlano(planoDTO.getTipoPlano());
        plano.setTipoVeiculo(planoDTO.getTipoVeiculo());
        plano.setDataPagamento(planoDTO.getDataPagamento());
        plano.setDataVencimento(planoDTO.getDataVencimento());
        plano.setStatus(planoDTO.isStatus());
        plano.setValorPlano(planoDTO.getValorPlano());


        return plano;
    }

    // Método de conversão de entidade para DTO
    PlanoDTO convertToDTO(Plano plano) {
        PlanoDTO planoDTO = new PlanoDTO();
        planoDTO.setId(plano.getId());
        planoDTO.setTipoPlano(plano.getTipoPlano());
        planoDTO.setTipoVeiculo(plano.getTipoVeiculo());
        planoDTO.setDataPagamento(plano.getDataPagamento());
        planoDTO.setDataVencimento(plano.getDataVencimento());
        planoDTO.setStatus(plano.isStatus());
        planoDTO.setValorPlano(plano.getValorPlano());


        return planoDTO;
    }

    // Método para atualizar dados de um plano a partir de um DTO
    private void updatePlanoFromDTO(Plano plano, PlanoDTO planoDTO) {
        plano.setTipoPlano(planoDTO.getTipoPlano());
        plano.setTipoVeiculo(planoDTO.getTipoVeiculo());
        plano.setDataPagamento(planoDTO.getDataPagamento());
        plano.setDataVencimento(planoDTO.getDataVencimento());
        plano.setStatus(planoDTO.isStatus());
        plano.setValorPlano(planoDTO.getValorPlano());


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
        if (planoDTO.getDataVencimento() == null) {
            throw new IllegalArgumentException("Data de vencimento não pode ser nula");
        }
        if (planoDTO.getDataPagamento() == null) {
            throw new IllegalArgumentException("Data de pagamento não pode ser nula");
        }
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
