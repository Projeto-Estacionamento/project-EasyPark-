package com.backend.EasyPark.util;


import com.backend.EasyPark.model.dto.VeiculoDTO;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class VeiculoServiceTest {

    private VeiculoDTO veiculoDTO;

    @BeforeEach
    void setUp() {
        veiculoDTO = new VeiculoDTO();
        veiculoDTO.setPlaca("ABC1234");
        veiculoDTO.setTipoVeiculo(TipoVeiculo.CARRO);
    }

    @Test
    void validaCriacaoDeVeiculo() {
        assertNotNull(veiculoDTO, "O VeiculoDTO não deve ser nulo");
        assertEquals("ABC1234", veiculoDTO.getPlaca(), "A placa do veículo deve ser 'ABC1234'");
        assertEquals(TipoVeiculo.CARRO, veiculoDTO.getTipoVeiculo(), "O tipo do veículo deve ser 'CARRO'");
    }

    @Test
    void validaPlacaVeiculo() {
        String placaInvalida = "1234ABC";

        assertTrue(veiculoDTO.getPlaca().matches("[A-Z]{3}[0-9]{4}"), "A placa deve ser válida no formato 'ABC1234'");
        assertFalse(placaInvalida.matches("[A-Z]{3}[0-9]{4}"), "Placa inválida não deve ser aceita");
    }
}