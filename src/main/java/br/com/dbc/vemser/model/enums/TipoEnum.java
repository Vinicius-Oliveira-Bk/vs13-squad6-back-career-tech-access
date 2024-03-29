package br.com.dbc.vemser.model.enums;

import lombok.Getter;

@Getter
public enum TipoEnum {
    RESIDENCIAL(1),
    COMERCIAL(2);

    private final int valor;

    TipoEnum(int valor) {
        this.valor = valor;
    }

    public static TipoEnum fromValor(int valor) {
        for (TipoEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}
