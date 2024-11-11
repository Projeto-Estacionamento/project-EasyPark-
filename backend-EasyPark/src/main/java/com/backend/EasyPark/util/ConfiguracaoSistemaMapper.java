package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.entities.ConfiguracaoSistema;

@Component
public class ConfiguracaoSistemaMapper {

    public ConfiguracaoSistema toEntity(ConfiguracaoSistemaDTO dto) {
        if (dto == null) {
            return null;
        }

        ConfiguracaoSistema configuracao = new ConfiguracaoSistema();
        configuracao.setId(dto.getId());
        configuracao.setQtdMoto(dto.getQtdMoto());
        configuracao.setQtdCarro(dto.getQtdCarro());
        configuracao.setValorHoraMoto(dto.getValorHoraMoto());
        configuracao.setValorHoraCarro(dto.getValorHoraCarro());
        configuracao.setValorDiariaCarro(dto.getValorDiariaCarro());
        configuracao.setValorDiariaMoto(dto.getValorDiariaMoto());
        configuracao.setHoraMaximaAvulso(dto.getHoraMaximaAvulso());
        configuracao.setValorMensalidadeMoto(dto.getValorMensalidadeMoto());
        configuracao.setValorMensalidadeCarro(dto.getValorMensalidadeCarro());

        return configuracao;
    }

    public ConfiguracaoSistemaDTO toDTO(ConfiguracaoSistema entity) {
        if (entity == null) {
            return null;
        }
        return new ConfiguracaoSistemaDTO(
            entity.getId(),
            entity.getQtdMoto(),
            entity.getQtdCarro(),
            entity.getValorHoraMoto(),
            entity.getValorHoraCarro(),
            entity.getValorDiariaCarro(),
            entity.getValorDiariaMoto(),
            entity.getHoraMaximaAvulso(),
            entity.getValorMensalidadeCarro(),
            entity.getValorMensalidadeMoto()
        );
    }

    public void updateEntityFromDTO(ConfiguracaoSistema configuracao, ConfiguracaoSistemaDTO dto) {
        if (dto == null) {
            return;
        }
        configuracao.setQtdMoto(dto.getQtdMoto());
        configuracao.setQtdCarro(dto.getQtdCarro());
        configuracao.setValorHoraMoto(dto.getValorHoraMoto());
        configuracao.setValorHoraCarro(dto.getValorHoraCarro());
        configuracao.setValorDiariaCarro(dto.getValorDiariaCarro());
        configuracao.setValorDiariaMoto(dto.getValorDiariaMoto());
        configuracao.setHoraMaximaAvulso(dto.getHoraMaximaAvulso());
        configuracao.setValorMensalidadeMoto(dto.getValorMensalidadeCarro());
        configuracao.setValorMensalidadeMoto(dto.getValorMensalidadeMoto());
    }
}
