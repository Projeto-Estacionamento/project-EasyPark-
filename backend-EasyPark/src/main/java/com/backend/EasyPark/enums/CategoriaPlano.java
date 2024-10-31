package com.backend.EasyPark.enums;

public enum CategoriaPlano {
    CARRO(100.0),  // valor para carro
    MOTO(50.0);    // valor para moto

    private final double valorMensal;

    CategoriaPlano(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public double getValorMensal() {
        return valorMensal;
    }
}

