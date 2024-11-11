package com.backend.EasyPark.util.validacao;

import java.util.regex.Pattern;

public class ValidacaoVeiculo {

    private static final String PLACA_REGEX = "^[A-Z]{3}\\d{4}$"; // Exemplo de regex para placa

    public boolean isPlacaValida(String placa) {
        return placa != null && Pattern.matches(PLACA_REGEX, placa);
    }
}
