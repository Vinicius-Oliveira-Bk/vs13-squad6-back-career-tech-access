package com.dbc.model.enums;

public enum TipoClienteEnum {
    ESTUDANTE (1),
    PCD (2),
    PROFISSIONAL_REALOCAO (3);

    private final int tipoCliente;

    TipoClienteEnum(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }
}