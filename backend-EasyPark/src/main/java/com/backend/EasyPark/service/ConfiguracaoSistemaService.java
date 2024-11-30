package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.entities.ConfiguracaoSistema;
import com.backend.EasyPark.repository.ConfiguracaoSistemaRepository;
=======
import com.backend.EasyPark.model.entities.Ticket;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import com.backend.EasyPark.model.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.model.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.model.entities.ConfiguracaoSistema;
import com.backend.EasyPark.model.repository.ConfiguracaoSistemaRepository;
>>>>>>> Stashed changes
import com.backend.EasyPark.util.ConfiguracaoSistemaMapper;

@Service
public class ConfiguracaoSistemaService {

    private final ConfiguracaoSistemaRepository configuracaoSistemaRepository;
    private final ConfiguracaoSistemaMapper configuracaoSistemaMapper;
<<<<<<< Updated upstream

    @Autowired
    public ConfiguracaoSistemaService(ConfiguracaoSistemaRepository configuracaoSistemaRepository,
                                      ConfiguracaoSistemaMapper configuracaoSistemaMapper) {
        this.configuracaoSistemaRepository = configuracaoSistemaRepository;
        this.configuracaoSistemaMapper = configuracaoSistemaMapper;
=======
    private final TicketRepository ticketRepository;

    @Autowired
    public ConfiguracaoSistemaService(ConfiguracaoSistemaRepository configuracaoSistemaRepository,
                                      ConfiguracaoSistemaMapper configuracaoSistemaMapper, TicketRepository ticketRepository) {
        this.configuracaoSistemaRepository = configuracaoSistemaRepository;
        this.configuracaoSistemaMapper = configuracaoSistemaMapper;
        this.ticketRepository = ticketRepository;
>>>>>>> Stashed changes
    }

    public ConfiguracaoSistemaDTO criarConfiguracao(ConfiguracaoSistemaDTO configuracaoDTO) {
        ConfiguracaoSistema configuracao = configuracaoSistemaMapper.toEntity(configuracaoDTO);
        ConfiguracaoSistema savedConfiguracao = configuracaoSistemaRepository.save(configuracao);
        return configuracaoSistemaMapper.toDTO(savedConfiguracao);
    }

    public ConfiguracaoSistemaDTO buscarConfiguracaoAtual() {
<<<<<<< Updated upstream
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdAsc()
=======
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdDesc()
>>>>>>> Stashed changes
                .orElseThrow(() -> new RuntimeException("Nenhuma configuração encontrada"));
        return configuracaoSistemaMapper.toDTO(configuracao);
    }

    public ConfiguracaoSistemaDTO atualizarConfiguracao(Integer id, ConfiguracaoSistemaDTO configuracaoDTO) {
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
<<<<<<< Updated upstream
=======


    //METODO PARA ATIVAR O BOTÃO DOS STATUS
    public void ativarContagemEMostrar(boolean mostrar) {
        // Busca a configuração do sistema
        ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Configuração do sistema não encontrada"));

        // Atualiza a configuração para ativar ou desativar a contagem de veículos
        configuracao.setMostrar(mostrar);
        configuracaoSistemaRepository.save(configuracao); // Salva a configuração atualizada

        // Se "mostrar" for verdadeiro, inicializa contadores com os valores definidos na configuração
        if (mostrar) {
            int qtdCarro = configuracao.getQtdCarro();
            int qtdMoto = configuracao.getQtdMoto();

            // Busca todos os tickets ativos
            List<Ticket> ticketsAtivos = ticketRepository.findAll(); // Supondo que todos os tickets são considerados ativos

            // Conta os veículos por tipo
            for (Ticket ticket : ticketsAtivos) {
                if (ticket.getTipoVeiculo() == TipoVeiculo.CARRO) {
                    qtdCarro--;
                } else if (ticket.getTipoVeiculo() == TipoVeiculo.MOTO) {
                    qtdMoto--;
                }
            }

            // Atualiza a configuração com as novas quantidades
            configuracao.setQtdCarro(qtdCarro);
            configuracao.setQtdMoto(qtdMoto);
            configuracaoSistemaRepository.save(configuracao); // Salva a configuração atualizada
        }
    }
>>>>>>> Stashed changes
}
