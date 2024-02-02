package br.com.dbc.vemser.model.enums;

import lombok.Getter;

@Getter
public enum StatusAgendaEnum {
    DISPONIVEL(1),
    AGENDADO(2),
    PRESENTE(3);

    private final int valor;

    StatusAgendaEnum(int valor) {
        this.valor = valor;
    }

    public static StatusAgendaEnum fromValor(int valor) {
        for (StatusAgendaEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        System.err.println("Valor inválido: " + valor);
        return null;
    }
}
