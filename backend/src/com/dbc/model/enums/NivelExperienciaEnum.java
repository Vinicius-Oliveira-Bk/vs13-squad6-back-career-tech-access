package com.dbc.model.enums;

public enum NivelExperienciaEnum {
    JUNIOR(1),
    PLENO(2),
    SENIOR(3);

    private final int valor;

    NivelExperienciaEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static NivelExperienciaEnum fromValor(int valor) {
        for (NivelExperienciaEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        System.err.println("Valor inválido: " + valor);
        return null;
    }
}
