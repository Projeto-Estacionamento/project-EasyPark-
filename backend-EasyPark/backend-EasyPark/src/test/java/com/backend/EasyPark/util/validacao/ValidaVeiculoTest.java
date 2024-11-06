package com.backend.EasyPark.util.validacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidaVeiculoTest {

    private final ValidacaoVeiculo validacaoVeiculo = new ValidacaoVeiculo();

    @Test
    public void testPlacaValida() {
        assertTrue(validacaoVeiculo.isPlacaValida("ABC1234")); // Placa válida
    }

    @Test
    public void testPlacaInvalida() {
        assertFalse(validacaoVeiculo.isPlacaValida("AB1234")); // Placa inválida (formato errado)
        assertFalse(validacaoVeiculo.isPlacaValida("ABC123")); // Placa inválida (formato errado)
        assertFalse(validacaoVeiculo.isPlacaValida(null)); // Placa inválida (nula)
    }
}
