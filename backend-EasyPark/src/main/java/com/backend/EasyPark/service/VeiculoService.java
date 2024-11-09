package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Fabricante;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.FabricanteRepository;
import com.backend.EasyPark.repository.VeiculoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    private static final Pattern PLACA_ANTIGA = Pattern.compile("^[A-Z]{3}\\d{4}$");
    private static final Pattern PLACA_MERCOSUL = Pattern.compile("^[A-Z]{3}\\d[A-Z]\\d{2}$");

    @Transactional
    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) {
        validarPlaca(veiculoDTO.getPlaca());
        Veiculo veiculo = convertToEntity(veiculoDTO);
        Veiculo savedVeiculo = veiculoRepository.save(veiculo);
        return convertToDTO(savedVeiculo);
    }

    public VeiculoDTO buscarVeiculoPorId(Integer id) throws EstacionamentoException {
        return convertToDTO(veiculoRepository.findById(id).orElseThrow(() -> new EstacionamentoException("Veículo não encontrado")));
    }

    public List<VeiculoDTO> listarVeiculos() {
        return veiculoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public VeiculoDTO atualizarVeiculo(Integer id, VeiculoDTO veiculoDTO) {
        validarPlaca(veiculoDTO.getPlaca());
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    updateVeiculoFromDTO(veiculo, veiculoDTO);
                    return convertToDTO(veiculoRepository.save(veiculo));
                })
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    @Transactional
    public void deletarVeiculo(Integer id) {
        veiculoRepository.deleteById(id);
    }

    public List<VeiculoDTO> listarVeiculosOcupandoVaga() {
        return veiculoRepository.findByOcupandoVagaTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VeiculoDTO> listarVeiculosPorTipo(String tipoVeiculo) {
        return veiculoRepository.findByTipoVeiculo(tipoVeiculo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Veiculo convertToEntity(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(veiculoDTO.getId());
        veiculo.setPlaca(veiculoDTO.getPlaca().toUpperCase()); // Garante que a placa seja salva em maiúsculas
        veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
        veiculo.setOcupandoVaga(veiculoDTO.isOcupandoVaga());
        if (veiculoDTO.getFabricanteDTO() != null) {
            veiculo.setFabricante(fabricanteRepository.findById(veiculoDTO.getFabricanteDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
        }
        return veiculo;
    }

    public VeiculoDTO convertToDTO(Veiculo veiculo) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setId(veiculo.getId());
        veiculoDTO.setPlaca(veiculo.getPlaca());
        veiculoDTO.setTipoVeiculo(veiculo.getTipoVeiculo());
        veiculoDTO.setOcupandoVaga(veiculo.isOcupandoVaga());
        if (veiculo.getFabricante() != null) {
            veiculoDTO.setFabricanteDTO(new FabricanteDTO(veiculo.getFabricante().getId(),
                    veiculo.getFabricante().getMarca(),
                    veiculo.getFabricante().getModelo(),
                    veiculo.getFabricante().getAno()));
        }
        return veiculoDTO;
    }

    public void updateVeiculoFromDTO(Veiculo veiculo, VeiculoDTO veiculoDTO) {
        veiculo.setPlaca(veiculoDTO.getPlaca().toUpperCase()); // Garante que a placa seja atualizada em maiúsculas
        veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
        veiculo.setOcupandoVaga(veiculoDTO.isOcupandoVaga());
        // veiculo.setFabricante(fabricanteRepository.findById(veiculoDTO.getFabricanteDTO().getId())
        //             .orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
        if (veiculoDTO.getFabricanteDTO() != null) {
            if (veiculo.getFabricante() == null) {
                veiculo.setFabricante(new Fabricante());
            }
            fabricanteRepository.findById(veiculoDTO.getFabricanteDTO().getId())
                    .ifPresent(fabricante -> veiculo.setFabricante(fabricante));
        }
    }

    private void validarPlaca(String placa) {
        if (placa == null || (!PLACA_ANTIGA.matcher(placa).matches() && !PLACA_MERCOSUL.matcher(placa).matches())) {
            throw new IllegalArgumentException("Placa inválida. Deve estar no formato antigo (ABC1234) ou Mercosul (ABC1D23).");
        }
    }
}

