package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.entities.Endereco;
import com.backend.EasyPark.repository.EnderecoRepository;
import com.backend.EasyPark.util.EnderecoMapper;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(enderecoMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Integer id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return enderecoMapper.toDTO(endereco);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findByCep(String cep) {
        List<Endereco> enderecos = enderecoRepository.findByCep(cep);
        return enderecos.stream().map(enderecoMapper::toDTO).collect(Collectors.toList());
    }


    public EnderecoDTO create(EnderecoDTO enderecoDTO) {
        validarEndereco(enderecoDTO);
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDTO(endereco);
    }


    public EnderecoDTO update(Integer id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoMapper.updateEntityFromDTO(enderecoDTO, endereco);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDTO(endereco);
    }


    public void delete(Integer id) {
        enderecoRepository.deleteById(id);
    }

    private void validarEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getCidade() == null || enderecoDTO.getCidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia");
        }
        if (enderecoDTO.getEstado() == null || enderecoDTO.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("Estado não pode ser vazio");
        }
        if (enderecoDTO.getCep() == null || !enderecoDTO.getCep().matches("^\\d{5}-\\d{3}$")) {
            throw new IllegalArgumentException("CEP inválido. Use o formato: XXXXX-XXX");
        }
    }
}
