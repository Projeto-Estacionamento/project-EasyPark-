package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.entities.ConfiguracaoSistema;
=======
import com.backend.EasyPark.model.dto.ConfiguracaoSistemaDTO;
import com.backend.EasyPark.model.entities.ConfiguracaoSistema;
>>>>>>> Stashed changes

@Component
public class ConfiguracaoSistemaMapper {

    public ConfiguracaoSistema toEntity(ConfiguracaoSistemaDTO dto) {
        if (dto == null) {
            return null;
        }

        ConfiguracaoSistema configuracao = new ConfiguracaoSistema();
        configuracao.setId(dto.getId());
<<<<<<< Updated upstream
=======
        configuracao.setMostrar(dto.isMostrar());
>>>>>>> Stashed changes
        configuracao.setQtdMoto(dto.getQtdMoto());
        configuracao.setQtdCarro(dto.getQtdCarro());
        configuracao.setValorHoraMoto(dto.getValorHoraMoto());
        configuracao.setValorHoraCarro(dto.getValorHoraCarro());
        configuracao.setValorDiariaCarro(dto.getValorDiariaCarro());
        configuracao.setValorDiariaMoto(dto.getValorDiariaMoto());
        configuracao.setHoraMaximaAvulso(dto.getHoraMaximaAvulso());

        return configuracao;
    }

    public ConfiguracaoSistemaDTO toDTO(ConfiguracaoSistema entity) {
        if (entity == null) {
            return null;
        }
        return new ConfiguracaoSistemaDTO(
            entity.getId(),
<<<<<<< Updated upstream
=======
            entity.isMostrar(),
>>>>>>> Stashed changes
            entity.getQtdMoto(),
            entity.getQtdCarro(),
            entity.getValorHoraMoto(),
            entity.getValorHoraCarro(),
            entity.getValorDiariaCarro(),
            entity.getValorDiariaMoto(),
            entity.getHoraMaximaAvulso()

        );
    }

    public void updateEntityFromDTO(ConfiguracaoSistema configuracao, ConfiguracaoSistemaDTO dto) {
        if (dto == null) {
            return;
        }
<<<<<<< Updated upstream
=======
        configuracao.setMostrar(dto.isMostrar());
>>>>>>> Stashed changes
        configuracao.setQtdMoto(dto.getQtdMoto());
        configuracao.setQtdCarro(dto.getQtdCarro());
        configuracao.setValorHoraMoto(dto.getValorHoraMoto());
        configuracao.setValorHoraCarro(dto.getValorHoraCarro());
        configuracao.setValorDiariaCarro(dto.getValorDiariaCarro());
        configuracao.setValorDiariaMoto(dto.getValorDiariaMoto());
        configuracao.setHoraMaximaAvulso(dto.getHoraMaximaAvulso());
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
