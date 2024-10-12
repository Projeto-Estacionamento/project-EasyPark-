package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.entities.ConfiguracaoSistema;
import com.backend.EasyPark.repository.ConfiguracaoSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfiguracaoSistemaService {

    @Autowired
    private ConfiguracaoSistemaRepository configuracaoSistemaRepository;

    public ConfiguracaoSistemaDTO criarConfiguracao(ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistema configuracao = convertToEntity(configuracaoDTO);
        ConfiguracaoSistema savedConfiguracao = configuracaoSistemaRepository.save(configuracao);
        return convertToDTO(savedConfiguracao);
    }

    public ConfiguracaoSistemaDTO buscarConfiguracaoAtual() {
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Nenhuma configuração encontrada"));
        return convertToDTO(configuracao);
    }

    public ConfiguracaoSistemaDTO atualizarConfiguracao(Long id, ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada"));
        updateConfiguracao(configuracao, configuracaoDTO);
        ConfiguracaoSistema updatedConfiguracao = configuracaoSistemaRepository.save(configuracao);
        return convertToDTO(updatedConfiguracao);
    }

    public List<ConfiguracaoSistemaDTO> listarTodasConfiguracoes() {
        return configuracaoSistemaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConfiguracaoSistema convertToEntity(ConfiguracaoSistemaDTO dto) {
        ConfiguracaoSistema configuracao = new ConfiguracaoSistema();
        configuracao.setId(dto.getId());
        configuracao.setQtdMoto(dto.getQtdMoto());
        configuracao.setQtdCarro(dto.getQtdCarro());
        configuracao.setValorHoraMoto(dto.getValorHoraMoto());
        configuracao.setValorHoraCarro(dto.getValorHoraCarro());
        configuracao.setValorDiariaCarro(dto.getValorDiariaCarro());
        configuracao.setValorDiariaMoto(dto.getValorDiariaMoto());
        configuracao.setHoraMaximaAvulso(dto.getHoraMaximaAvulso());
        return configuracao;
    }

    private ConfiguracaoSistemaDTO convertToDTO(ConfiguracaoSistema configuracao) {
        return new ConfiguracaoSistemaDTO(
                configuracao.getId(),
                configuracao.getQtdMoto(),
                configuracao.getQtdCarro(),
                configuracao.getValorHoraMoto(),
                configuracao.getValorHoraCarro(),
                configuracao.getValorDiariaCarro(),
                configuracao.getValorDiariaMoto(),
                configuracao.getHoraMaximaAvulso()
        );
    }

    private void updateConfiguracao(ConfiguracaoSistema configuracao, ConfiguracaoSistemaDTO dto) {
        configuracao.setQtdMoto(dto.getQtdMoto());
        configuracao.setQtdCarro(dto.getQtdCarro());
        configuracao.setValorHoraMoto(dto.getValorHoraMoto());
        configuracao.setValorHoraCarro(dto.getValorHoraCarro());
        configuracao.setValorDiariaCarro(dto.getValorDiariaCarro());
        configuracao.setValorDiariaMoto(dto.getValorDiariaMoto());
        configuracao.setHoraMaximaAvulso(dto.getHoraMaximaAvulso());
    }
}
