package com.backend.EasyPark.util;

import com.backend.EasyPark.controller.EnderecoController;
import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.dto.UsuarioDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Endereco;
import com.backend.EasyPark.entities.Fabricante;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.entities.Veiculo;

import com.backend.EasyPark.repository.UsuarioRepository;
import com.backend.EasyPark.service.FabricanteService;
import com.backend.EasyPark.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VeiculoMapper {

    private static UsuarioRepository usuarioRepository;
    private static FabricanteService fabricanteService;

    @Autowired
    public VeiculoMapper(UsuarioRepository usuarioRepository, FabricanteService fabricanteService) {
        VeiculoMapper.usuarioRepository = usuarioRepository;
        VeiculoMapper.fabricanteService = fabricanteService;
    }

    public static VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setPlaca(veiculo.getPlaca());
        dto.setTipoVeiculo(veiculo.getTipoVeiculo());
        dto.setOcupandoVaga(veiculo.isOcupandoVaga());

        // Seta o ID do usuário se existir
        if (veiculo.getUsuario() != null) {
            dto.setIdUsuarioDTO(veiculo.getUsuario().getId());
        }

        if (veiculo.getFabricante() != null) {
            dto.setFabricanteDTO(FabricanteMapper.toDTO(veiculo.getFabricante()));
        }

        return dto;
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) {
            return null;
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setTipoVeiculo(dto.getTipoVeiculo());
        veiculo.setOcupandoVaga(dto.isOcupandoVaga());

        // Associa o usuário pelo ID
        if (dto.getIdUsuarioDTO() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuarioDTO())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.getIdUsuarioDTO()));
            veiculo.setUsuario(usuario);
        }

        if (dto.getFabricanteDTO() != null) {
            veiculo.setFabricante(FabricanteMapper.toEntity(dto.getFabricanteDTO()));
        }

        return veiculo;
    }
    // Converte uma lista de Veiculo para uma lista de VeiculoDTO
    public static List<VeiculoDTO> toDtoList(List<Veiculo> veiculos) {
        return veiculos.stream().map(VeiculoMapper::toDTO).toList();
    }

    // Converte uma lista de VeiculoDTO para uma lista de Veiculo
    public static List<Veiculo> toEntityList(List<VeiculoDTO> veiculosDTO) {
        return veiculosDTO.stream().map(VeiculoMapper::toEntity).toList();
    }

    public static void updateVeiculoFromDTO(Veiculo veiculo, VeiculoDTO veiculoDTO) {
        veiculo.setPlaca(veiculoDTO.getPlaca().toUpperCase()); // Garante que a placa seja atualizada em maiúsculas
        veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
        veiculo.setOcupandoVaga(veiculoDTO.isOcupandoVaga());
        //veiculo.setFabricante(fabricanteRepository.findById(veiculoDTO.getFabricanteDTO().getId());
        //             .orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
        if (veiculoDTO.getFabricanteDTO() != null) {
            if (veiculo.getFabricante() == null) {
                veiculo.setFabricante(new Fabricante());
            }
            FabricanteDTO fabricanteDTO = veiculoDTO.getFabricanteDTO();
            FabricanteDTO fabricante = fabricanteService.buscarPorId(fabricanteDTO.getId());
            veiculo.setFabricante(FabricanteMapper.toEntity(fabricante));
        }
    }
}

