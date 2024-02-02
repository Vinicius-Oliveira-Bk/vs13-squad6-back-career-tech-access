package br.com.dbc.vemser.model.enums;

import lombok.Getter;

@Getter
public enum PlanoEnum {
    GRATUITO(1),
    BASICO(2),
    PREMIUM(3);

    private final int valor;

    PlanoEnum(int valor) {
        this.valor = valor;
    }

    public static PlanoEnum fromValor(int valor) {
        for (PlanoEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}
