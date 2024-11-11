package com.backend.EasyPark.enums;

public enum TipoPlano {
    CARRO(100.0),  // valor para carro
    MOTO(50.0);    // valor para moto

    private final double valorMensal;

    TipoPlano(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public double getValorMensal() {
        return valorMensal;
    }
}

