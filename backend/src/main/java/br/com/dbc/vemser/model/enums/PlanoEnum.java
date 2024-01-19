package br.com.dbc.vemser.model.enums;

public enum PlanoEnum {
    GRATUITO(1),
    BASICO(2),
    PREMIUM(3);

    private final int valor;

    PlanoEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static PlanoEnum fromValor(int valor) {
        for (PlanoEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        System.err.println("Valor inválido: " + valor);
        return null;
    }
}
