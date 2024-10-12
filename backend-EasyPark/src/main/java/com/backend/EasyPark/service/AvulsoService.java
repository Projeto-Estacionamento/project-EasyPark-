package com.backend.EasyPark.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.AvulsoDTO;
import com.backend.EasyPark.entities.Avulso;
import com.backend.EasyPark.repository.AvulsoRepository;

@Service
public class AvulsoService {

    @Autowired
    private AvulsoRepository avulsoRepository;

    public AvulsoDTO criarAvulso(AvulsoDTO avulsoDTO) {
        Avulso avulso = convertToEntity(avulsoDTO);
        Avulso savedAvulso = avulsoRepository.save(avulso);
        return convertToDTO(savedAvulso);
    }

    public AvulsoDTO buscarAvulsoPorId(Long id) {
        Avulso avulso = avulsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avulso não encontrado"));
        return convertToDTO(avulso);
    }

    public AvulsoDTO buscarAvulsoPorPlaca(String placaVeiculo) {
        Avulso avulso = avulsoRepository.findTopByPlacaVeiculoOrderByHoraChegadaDesc(placaVeiculo)
                .orElseThrow(() -> new RuntimeException("Avulso não encontrado para esta placa"));
        return convertToDTO(avulso);
    }

    public List<AvulsoDTO> buscarAvulsosPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return avulsoRepository.findByHoraChegadaBetween(inicio, fim).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AvulsoDTO> buscarAvulsosEmAberto() {
        return avulsoRepository.findByHoraSaidaIsNull().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AvulsoDTO> buscarAvulsosPorValorTotal(boolean valorTotal) {
        return avulsoRepository.findByValorTotal(valorTotal).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AvulsoDTO atualizarAvulso(Long id, AvulsoDTO avulsoDTO) {
        Avulso avulso = avulsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avulso não encontrado"));
        updateAvulsoFromDTO(avulso, avulsoDTO);
        Avulso updatedAvulso = avulsoRepository.save(avulso);
        return convertToDTO(updatedAvulso);
    }

    public void deletarAvulso(Long id) {
        avulsoRepository.deleteById(id);
    }

    private Avulso convertToEntity(AvulsoDTO avulsoDTO) {
        Avulso avulso = new Avulso();
        avulso.setId(avulsoDTO.getId());
        avulso.setPlacaVeiculo(avulsoDTO.getPlacaVeiculo());
        avulso.setHoraChegada(avulsoDTO.getHoraChegada());
        avulso.setHoraSaida(avulsoDTO.getHoraSaida());
        avulso.setQtdHora(avulsoDTO.getQtdHora());
        avulso.setValorTotal(avulsoDTO.isValorTotal());
        return avulso;
    }

    private AvulsoDTO convertToDTO(Avulso avulso) {
        return new AvulsoDTO(
                avulso.getId(),
                avulso.getPlacaVeiculo(),
                avulso.getHoraChegada(),
                avulso.getHoraSaida(),
                avulso.getQtdHora(),
                avulso.isValorTotal()
        );
    }

    private void updateAvulsoFromDTO(Avulso avulso, AvulsoDTO avulsoDTO) {
        avulso.setPlacaVeiculo(avulsoDTO.getPlacaVeiculo());
        avulso.setHoraChegada(avulsoDTO.getHoraChegada());
        avulso.setHoraSaida(avulsoDTO.getHoraSaida());
        avulso.setQtdHora(avulsoDTO.getQtdHora());
        avulso.setValorTotal(avulsoDTO.isValorTotal());
    }
}
