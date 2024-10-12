package com.backend.EasyPark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.entities.Endereco;
import com.backend.EasyPark.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return convertToDTO(endereco);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findByCep(String cep) {
        List<Endereco> enderecos = enderecoRepository.findByCep(cep);
        return enderecos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public EnderecoDTO create(EnderecoDTO enderecoDTO) {
        validarEndereco(enderecoDTO);
        Endereco endereco = convertToEntity(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return convertToDTO(endereco);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        updateEnderecoFromDTO(endereco, enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return convertToDTO(endereco);
    }

    @Transactional
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco convertToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
        return endereco;
    }

    public EnderecoDTO convertToDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCep(endereco.getCep());
        return enderecoDTO;
    }

    public void updateEnderecoFromDTO(Endereco endereco, EnderecoDTO enderecoDTO) {
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
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
