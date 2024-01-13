
package com.dbc.model.enums;
public enum TipoEstudanteEnum {
    ENSINO_FUNDAMENTAL(1),
    ENSINO_MEDIO(2),
    ENSINO_SUPERIOR(3);

    private final int valor;

    TipoEstudanteEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoEstudanteEnum fromValor(int valor) {
        for (TipoEstudanteEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        System.err.println("Valor inválido: " + valor);
        return null;
    }
}
