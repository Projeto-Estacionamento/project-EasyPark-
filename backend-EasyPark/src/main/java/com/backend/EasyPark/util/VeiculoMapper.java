package com.backend.EasyPark.util;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Fabricante;
import com.backend.EasyPark.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VeiculoMapper {

    private final FabricanteMapper fabricanteMapper;

    @Autowired
    public VeiculoMapper(FabricanteMapper fabricanteMapper) {
        this.fabricanteMapper = fabricanteMapper;
    }

    public VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) {
            return null;
        }

        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setPlaca(veiculo.getPlaca());
        dto.setTipoVeiculo(veiculo.getTipoVeiculo());
        dto.setOcupandoVaga(veiculo.isOcupandoVaga());

        // Convertendo o Fabricante para FabricanteDTO
        dto.setFabricanteDTO(veiculo.getFabricante() != null ?
                fabricanteMapper.toDTO(veiculo.getFabricante()) : null);

        return dto;
    }

    public Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
        veiculo.setOcupandoVaga(dto.isOcupandoVaga());

        // Convertendo o FabricanteDTO para Fabricante
        veiculo.setFabricante(dto.getFabricanteDTO() != null ?
                fabricanteMapper.toEntity(dto.getFabricanteDTO()) : null);

        return veiculo;
    }
    // Converte uma lista de Veiculo para uma lista de VeiculoDTO
    public List<VeiculoDTO> toDTO(List<Veiculo> veiculos) {
        return veiculos.stream().map(this::toDTO).collect(Collectors.toList());
    }


    // Converte uma lista de VeiculoDTO para uma lista de Veiculo
    public List<Veiculo> toEntity(List<VeiculoDTO> veiculosDTO) {
        return veiculosDTO.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
