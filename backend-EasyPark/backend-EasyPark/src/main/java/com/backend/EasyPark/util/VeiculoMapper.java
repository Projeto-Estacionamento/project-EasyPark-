package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.FabricanteDTO; // Certifique-se de que você tenha esta importação
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VeiculoMapper {

    @Autowired
    private FabricanteMapper fabricanteMapper; // Injeção do fabricanteMapper

    public Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
        veiculo.setOcupandoVaga(dto.isOcupandoVaga());

        // Mapeia o fabricante
        if (dto.getFabricanteDTO() != null) {
            veiculo.setFabricante(fabricanteMapper.toEntity(dto.getFabricanteDTO()));
        }

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

        // Mapeia o fabricante
        if (entity.getFabricante() != null) {
            dto.setFabricanteDTO(fabricanteMapper.toDTO(entity.getFabricante()));
        }

        return dto;
    }

    public List<Veiculo> toEntity(List<VeiculoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<VeiculoDTO> toDTO(List<Veiculo> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void updateVeiculoFromDTO(List<Veiculo> veiculos, List<VeiculoDTO> dtos) {
        for (int i = 0; i < dtos.size(); i++) {
            if (i < veiculos.size()) {
                Veiculo veiculo = veiculos.get(i);
                VeiculoDTO dto = dtos.get(i);
                veiculo.setPlaca(dto.getPlaca());
                veiculo.setTipoVeiculo(dto.getTipoVeiculo());
                veiculo.setOcupandoVaga(dto.isOcupandoVaga());

                // Atualiza o fabricante se necessário
                if (dto.getFabricanteDTO() != null) {
                    if (veiculo.getFabricante() == null) {
                        veiculo.setFabricante(fabricanteMapper.toEntity(dto.getFabricanteDTO()));
                    } else {
                        fabricanteMapper.updateEntityFromDTO(veiculo.getFabricante(), dto.getFabricanteDTO());
                    }
                } else {
                    veiculo.setFabricante(null); // Define como null se não houver fabricante
                }
            } else {
                // Adiciona novos veículos se houver mais DTOs que entidades
                Veiculo novoVeiculo = toEntity(dtos.get(i));
                veiculos.add(novoVeiculo);
            }
        }
    }
}
