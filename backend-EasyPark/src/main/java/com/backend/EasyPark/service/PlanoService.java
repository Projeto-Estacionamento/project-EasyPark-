package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.entities.UsuarioPlano;
import com.backend.EasyPark.repository.PlanoRepository;
import com.backend.EasyPark.util.PlanoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private PlanoMapper planoMapper;


    public PlanoDTO criarPlano(PlanoDTO planoDTO) {
        UsuarioPlano usuarioPlano = planoRepository.save(planoMapper.toEntity(planoDTO));
        return planoMapper.toDTO(usuarioPlano);
    }

    public PlanoDTO buscarPlanoPorId(Integer id) {
        UsuarioPlano usuarioPlano = planoRepository.findById(id).orElse(null);
        return planoMapper.toDTO(usuarioPlano);
    }


    public List<PlanoDTO> listarPlanos() {
        List<UsuarioPlano> usuarioPlanos = planoRepository.findAll();
        return planoMapper.toPlanoListDTO(usuarioPlanos);
    }



}
