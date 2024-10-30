package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.repository.PlanoRepository;
import com.backend.EasyPark.util.PlanoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private PlanoMapper planoMapper;


    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        Plano plano = planoRepository.save(planoMapper.toEntity(planoDTO));
        return planoMapper.toDTO(plano);
    }

    public PlanoDTO buscarPlanoPorId(Integer id) {
        Plano plano = planoRepository.findById(id).orElse(null);
        return planoMapper.toDTO(plano);
    }


    public List<PlanoDTO> listarPlanos() {
        List<Plano> planos = planoRepository.findAll();
        return planoMapper.toPlanoListDTO(planos);
    }



}
