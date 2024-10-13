package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Veiculo;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setPlaca(dto.getPlaca().toUpperCase());
        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
        veiculo.setOcupandoVaga(dto.isOcupandoVaga());
        
        // O fabricante será definido no serviço, pois pode precisar ser buscado ou criado
        
        return veiculo;
    }

    public VeiculoDTO toDTO(Veiculo entity) {
        if (entity == null) {
            return null;
        }

        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setTipoVeiculo(entity.getTipoVeiculo());
        dto.setOcupandoVaga(entity.isOcupandoVaga());
        
        if (entity.getFabricante() != null) {
            FabricanteDTO fabricanteDTO = new FabricanteDTO(
                entity.getFabricante().getId(),
                entity.getFabricante().getMarca(),
                entity.getFabricante().getModelo(),
                entity.getFabricante().getAno()
            );
            dto.setFabricanteDTO(fabricanteDTO);
        }
        
        return dto;
    }

    public void updateVeiculoFromDTO(Veiculo veiculo, VeiculoDTO dto) {
        if (dto == null) {
            return;
        }
        veiculo.setPlaca(dto.getPlaca().toUpperCase());
        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
        veiculo.setOcupandoVaga(dto.isOcupandoVaga());
    }
}
