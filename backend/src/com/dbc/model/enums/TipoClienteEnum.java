package com.dbc.model.enums;

public enum TipoClienteEnum {
    ESTUDANTE (1),
    PCD (2),
    PROFISSIONAL_REALOCACAO (3),
    MENTOR(4);


    private final int tipoCliente;

    TipoClienteEnum(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }
}