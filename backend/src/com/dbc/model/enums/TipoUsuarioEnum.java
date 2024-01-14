package com.dbc.model.enums;

public enum TipoUsuarioEnum {
    ESTUDANTE (1),
    PCD (2),
    PROFISSIONAL_REALOCACAO (3),
    MENTOR(4);

    private final int valor;

    TipoUsuarioEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoUsuarioEnum fromValor(int valor) {
        for (TipoUsuarioEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        System.err.println("Valor inválido: " + valor);
        return null;
    }
}
