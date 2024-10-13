package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.entities.ConfiguracaoSistema;
import com.backend.EasyPark.repository.ConfiguracaoSistemaRepository;
import com.backend.EasyPark.util.ConfiguracaoSistemaMapper;

@Service
public class ConfiguracaoSistemaService {

    private final ConfiguracaoSistemaRepository configuracaoSistemaRepository;
    private final ConfiguracaoSistemaMapper configuracaoSistemaMapper;

    @Autowired
    public ConfiguracaoSistemaService(ConfiguracaoSistemaRepository configuracaoSistemaRepository,
                                      ConfiguracaoSistemaMapper configuracaoSistemaMapper) {
        this.configuracaoSistemaRepository = configuracaoSistemaRepository;
        this.configuracaoSistemaMapper = configuracaoSistemaMapper;
    }

    public ConfiguracaoSistemaDTO criarConfiguracao(ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistema configuracao = configuracaoSistemaMapper.toEntity(configuracaoDTO);
        ConfiguracaoSistema savedConfiguracao = configuracaoSistemaRepository.save(configuracao);
        return configuracaoSistemaMapper.toDTO(savedConfiguracao);
    }

    public ConfiguracaoSistemaDTO buscarConfiguracaoAtual() {
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Nenhuma configuração encontrada"));
        return configuracaoSistemaMapper.toDTO(configuracao);
    }

    public ConfiguracaoSistemaDTO atualizarConfiguracao(Long id, ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada"));
        configuracaoSistemaMapper.updateEntityFromDTO(configuracao, configuracaoDTO);
        ConfiguracaoSistema updatedConfiguracao = configuracaoSistemaRepository.save(configuracao);
        return configuracaoSistemaMapper.toDTO(updatedConfiguracao);
    }

    public List<ConfiguracaoSistemaDTO> listarTodasConfiguracoes() {
        return configuracaoSistemaRepository.findAll().stream()
                .map(configuracaoSistemaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BigDecimal getValorPorHora() {
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Configuração do sistema não encontrada"));
        return BigDecimal.valueOf(configuracao.getValorHoraCarro());
    }
}
