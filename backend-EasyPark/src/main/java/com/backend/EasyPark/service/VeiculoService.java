package com.backend.EasyPark.service;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.FabricanteDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.Fabricante;
import com.backend.EasyPark.entities.Veiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.FabricanteRepository;
import com.backend.EasyPark.repository.VeiculoRepository;

import com.backend.EasyPark.util.VeiculoMapper;
import jakarta.persistence.EntityNotFoundException;
=======
import com.backend.EasyPark.model.dto.VeiculoDTO;
import com.backend.EasyPark.model.entities.Veiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.repository.FabricanteRepository;
import com.backend.EasyPark.model.repository.VeiculoRepository;

import com.backend.EasyPark.util.VeiculoMapper;
import org.springframework.beans.BeanUtils;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    @Autowired
    private VeiculoMapper veiculoMapper;
=======
>>>>>>> Stashed changes

    private static final Pattern PLACA_ANTIGA = Pattern.compile("^[A-Z]{3}\\d{4}$");
    private static final Pattern PLACA_MERCOSUL = Pattern.compile("^[A-Z]{3}\\d[A-Z]\\d{2}$");

    @Transactional
<<<<<<< Updated upstream
    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) {
        validarPlaca(veiculoDTO.getPlaca());
=======
    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) throws EstacionamentoException {
        validarPlaca(veiculoDTO.getPlaca());
        Optional<Veiculo> validarVeiculoBanco = veiculoRepository.findByPlaca(veiculoDTO.getPlaca());
        if (validarVeiculoBanco.isPresent()) {
            throw new EstacionamentoException("Veiculo ja existe na Base de dados com a placa" + veiculoDTO.getPlaca());
        }
>>>>>>> Stashed changes
        Veiculo veiculo = VeiculoMapper.toEntity(veiculoDTO);
        Veiculo savedVeiculo = veiculoRepository.save(veiculo);
        return VeiculoMapper.toDTO(savedVeiculo);
    }

    public VeiculoDTO buscarVeiculoPorId(Integer id) throws EstacionamentoException {
        return VeiculoMapper.toDTO(veiculoRepository.findById(id).orElseThrow(() -> new EstacionamentoException("Veículo não encontrado")));
    }

    public List<VeiculoDTO> listarVeiculos() {
        return veiculoRepository.findAll().stream()
                .map(VeiculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
<<<<<<< Updated upstream
    public VeiculoDTO atualizarVeiculo(Integer id, VeiculoDTO veiculoDTO) {
        validarPlaca(veiculoDTO.getPlaca());
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculoMapper.updateVeiculoFromDTO(veiculo, veiculoDTO);
                    return VeiculoMapper.toDTO(veiculoRepository.save(veiculo));
                })
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
=======
    public VeiculoDTO atualizarVeiculo(VeiculoDTO veiculoDTO) throws EstacionamentoException {
       Veiculo veiculoEncontrado = VeiculoMapper.toEntity(buscarVeiculoPorId(veiculoDTO.getId()));
        BeanUtils.copyProperties(veiculoDTO, veiculoEncontrado);
      return VeiculoMapper.toDTO(veiculoRepository.save(veiculoEncontrado));
>>>>>>> Stashed changes
    }

    @Transactional
    public void deletarVeiculo(Integer id) {
        veiculoRepository.deleteById(id);
    }

    public List<VeiculoDTO> listarVeiculosOcupandoVaga() {
        return veiculoRepository.findByOcupandoVagaTrue().stream()
                .map(VeiculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<VeiculoDTO> listarVeiculosPorTipo(String tipoVeiculo) {
        return veiculoRepository.findByTipoVeiculo(tipoVeiculo).stream()
                .map(VeiculoMapper::toDTO)
                .collect(Collectors.toList());
    }


<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    private void validarPlaca(String placa) {
        if (placa == null || (!PLACA_ANTIGA.matcher(placa).matches() && !PLACA_MERCOSUL.matcher(placa).matches())) {
            throw new IllegalArgumentException("Placa inválida. Deve estar no formato antigo (ABC1234) ou Mercosul (ABC1D23).");
        }
    }
}
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
