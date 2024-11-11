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

import com.backend.EasyPark.service.FabricanteService;
import com.backend.EasyPark.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VeiculoMapper {

    @Autowired
    private FabricanteService fabricanteService;


    public static VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) {
            return null;
        }
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setPlaca(veiculo.getPlaca());
        dto.setTipoVeiculo(veiculo.getTipoVeiculo());
        dto.setOcupandoVaga(veiculo.isOcupandoVaga());

        if (veiculo.getFabricante() != null) {
            FabricanteDTO fabricanteDTO = FabricanteMapper.toDTO(veiculo.getFabricante());
            dto.setFabricanteDTO(fabricanteDTO);
        }

        // Removendo a conversão circular do usuário
        if (veiculo.getUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(veiculo.getUsuario().getId());
            usuarioDTO.setNome(veiculo.getUsuario().getNome());
            usuarioDTO.setEmail(veiculo.getUsuario().getEmail());
            usuarioDTO.setTelefone(veiculo.getUsuario().getTelefone());
            usuarioDTO.setCpf(veiculo.getUsuario().getCpf());
            dto.setUsuarioDTO(usuarioDTO);
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

        // Converte FabricanteDTO para Fabricante
        if (dto.getFabricanteDTO() != null) {
            Fabricante fabricante = FabricanteMapper.toEntity(dto.getFabricanteDTO());
            veiculo.setFabricante(fabricante);
        }

        // Converte UsuarioDTO para Usuario de forma simplificada
        if (dto.getUsuarioDTO() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioDTO().getId());
            usuario.setNome(dto.getUsuarioDTO().getNome());
            usuario.setEmail(dto.getUsuarioDTO().getEmail());
            usuario.setTelefone(dto.getUsuarioDTO().getTelefone());
            usuario.setCpf(dto.getUsuarioDTO().getCpf());
            veiculo.setUsuario(usuario);
        }

        return veiculo;
    }

    // Converte uma lista de Veiculo para uma lista de VeiculoDTO
    public List<VeiculoDTO> toDtoList(List<Veiculo> veiculos) {
        if (veiculos == null || veiculos.isEmpty()) {
            return new ArrayList<>();
        }

        List<VeiculoDTO> veiculosDTO = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            veiculosDTO.add(toDTO(veiculo));
        }
        return veiculosDTO;
    }

    // Converte uma lista de VeiculoDTO para uma lista de Veiculo
    public List<Veiculo> toEntityList(List<VeiculoDTO> veiculosDTO) {
        if (veiculosDTO == null || veiculosDTO.isEmpty()) {
            return new ArrayList<>();
        }

        List<Veiculo> veiculos = new ArrayList<>();
        for (VeiculoDTO veiculoDTO : veiculosDTO) {
            veiculos.add(toEntity(veiculoDTO));
        }
        return veiculos;
    }

    public void updateVeiculoFromDTO(Veiculo veiculo, VeiculoDTO veiculoDTO) {
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

