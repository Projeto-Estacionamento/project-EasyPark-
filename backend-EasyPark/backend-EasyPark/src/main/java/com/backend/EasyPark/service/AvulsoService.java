/*
package com.backend.EasyPark.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.dto.AvulsoDTO;
import com.backend.EasyPark.entities.Avulso;
import com.backend.EasyPark.repository.AvulsoRepository;
import com.backend.EasyPark.util.AvulsoMapper;

@Service
public class AvulsoService {

    @Autowired
    private AvulsoRepository avulsoRepository;

    @Autowired
    private AvulsoMapper avulsoMapper;

    public AvulsoDTO criarAvulso(AvulsoDTO avulsoDTO) {
        Avulso avulso = avulsoMapper.toEntity(avulsoDTO);
        Avulso savedAvulso = avulsoRepository.save(avulso);
        return avulsoMapper.toDTO(savedAvulso);
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
        avulsoMapper.updateEntityFromDTO(avulso, avulsoDTO);
        Avulso updatedAvulso = avulsoRepository.save(avulso);
        return avulsoMapper.toDTO(updatedAvulso);
    }

    public void deletarAvulso(Long id) {
        avulsoRepository.deleteById(id);
    }

    private AvulsoDTO convertToDTO(Avulso avulso) {
        return avulsoMapper.toDTO(avulso);
    }
}
*/
