package br.com.dbc.vemser.model.enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {
    CLIENTE (1),
    PROFISSIONAL (2);
    private final int valor;

    TipoUsuarioEnum(int valor) {
        this.valor = valor;
    }

    public static TipoUsuarioEnum fromValor(int valor) {
        for (TipoUsuarioEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        return null;
    }
}
