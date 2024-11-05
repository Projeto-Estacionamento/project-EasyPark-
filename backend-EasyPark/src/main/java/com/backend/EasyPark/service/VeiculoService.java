package com.backend.EasyPark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.util.validacao.ValidacaoUsuario;
import com.backend.EasyPark.util.validacao.ValidarVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.repository.FabricanteRepository;
import com.backend.EasyPark.repository.VeiculoRepository;
import com.backend.EasyPark.util.VeiculoMapper;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private FabricanteService fabricanteService;

    @Autowired
    private VeiculoMapper veiculoMapper;


    private ValidarVeiculo validarVeiculo;

    private static final Pattern PLACA_ANTIGA = Pattern.compile("^[A-Z]{3}\\d{4}$");
    private static final Pattern PLACA_MERCOSUL = Pattern.compile("^[A-Z]{3}\\d[A-Z]\\d{2}$");

    @Transactional
    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) throws EstacionamentoException {
        validarVeiculo.validarCampoVeiculo(veiculoDTO);
        validarPlaca(veiculoDTO.getPlaca());
        Veiculo veiculo = convertToEntity(veiculoDTO);
        Veiculo savedVeiculo = veiculoRepository.save(veiculo);
        return convertToDTO(savedVeiculo);
    }

    public VeiculoDTO buscarVeiculoPorId(Integer id) {
        return veiculoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
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
        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
        if (veiculoDTO.getFabricanteDTO() != null) {
            FabricanteDTO fabricanteDTO = fabricanteService.buscarPorId(veiculoDTO.getFabricanteDTO().getId());
            veiculo.setFabricante(fabricanteRepository.findById(fabricanteDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
        }
        return veiculo;
    }

    public VeiculoDTO convertToDTO(Veiculo veiculo) {
        return veiculoMapper.toDTO(veiculo);
    }

    public void updateVeiculoFromDTO(Veiculo veiculo, VeiculoDTO veiculoDTO) {
        veiculo.setPlaca(veiculoDTO.getPlaca().toUpperCase()); // Garante que a placa seja atualizada em maiúsculas
        veiculo.setTipoVeiculo(veiculoDTO.getTipoVeiculo());
        veiculo.setOcupandoVaga(veiculoDTO.isOcupandoVaga());
        if (veiculoDTO.getFabricanteDTO() != null) {
            // Use o FabricanteService para buscar ou criar o fabricante
            FabricanteDTO fabricanteDTO = fabricanteService.buscarPorId(veiculoDTO.getFabricanteDTO().getId());
            veiculo.setFabricante(fabricanteRepository.findById(fabricanteDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
        } else {
            veiculo.setFabricante(null);
        }
    }

    private void validarPlaca(String placa) {
        if (placa == null || (!PLACA_ANTIGA.matcher(placa).matches() && !PLACA_MERCOSUL.matcher(placa).matches())) {
            throw new IllegalArgumentException("Placa inválida. Deve estar no formato antigo (ABC1234) ou Mercosul (ABC1D23).");
        }
    }

    public void validarVeiculo(List<VeiculoDTO> veiculoDTO) {
        List<VeiculoDTO> novaLista = new ArrayList<>();
        for (VeiculoDTO veiculo : veiculoDTO) {
            if (veiculo.getPlaca() == null || !veiculo.getPlaca().matches("^[A-Z]{3}\\d[A-Z0-9]\\d{2}$")) {
                throw new IllegalArgumentException("Placa do veículo inválida: " + veiculo.getPlaca());
            }
            novaLista.add(veiculo);
        }
    }
}
