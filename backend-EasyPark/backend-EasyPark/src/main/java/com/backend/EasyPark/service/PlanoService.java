package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.repository.PlanoRepository;
import com.backend.EasyPark.util.PlanoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private PlanoMapper planoMapper;

    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        Plano plano = planoMapper.toEntity(planoDTO);
        plano = planoRepository.save(plano);
        return planoMapper.toDTO(plano);
    }

    public PlanoDTO buscarPlanoPorId(Integer id) {
        Plano plano = planoRepository.findById(id).orElse(null);
        return planoMapper.toDTO(plano);
    }

    public List<PlanoDTO> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(planoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
