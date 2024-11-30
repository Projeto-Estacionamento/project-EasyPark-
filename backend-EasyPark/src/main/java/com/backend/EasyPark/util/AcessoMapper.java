package com.backend.EasyPark.util;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.AcessoDTO;
import com.backend.EasyPark.entities.Acesso;
=======
import com.backend.EasyPark.model.dto.AcessoDTO;
import com.backend.EasyPark.model.entities.Acesso;
>>>>>>> Stashed changes
import org.springframework.stereotype.Component;

@Component
public class AcessoMapper {

<<<<<<< Updated upstream
    public Acesso toEntity(AcessoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Acesso(dto.getUsername(), dto.getSenha(), dto.getTipoAcesso());
    }

    public AcessoDTO toDTO(Acesso entity) {
        if (entity == null) {
            return null;
        }
        return new AcessoDTO(entity.getUsername(), entity.getSenha(), entity.getTipoAcesso());
=======
    public static Acesso toEntity(AcessoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Acesso(dto.getId(),dto.getUsername(), dto.getSenha(), dto.getEmail(), dto.getTipoAcesso());
    }

    public static AcessoDTO toDTO(Acesso entity) {
        if (entity == null) {
            return null;
        }
        return new AcessoDTO(entity.getId(),entity.getUsername(), entity.getSenha(), entity.getEmail(), entity.getTipoAcesso());
>>>>>>> Stashed changes
    }
}
