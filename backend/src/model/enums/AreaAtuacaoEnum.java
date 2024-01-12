package enums;

public enum AreaAtuacaoEnum {
    TI(1),
    SAUDE(2),
    EDUCACAO(3),
    FINANCAS(4),
    MARKETING(5),
    JURIDICO(6),
    ENGENHARIA(7),
    DESIGN(8),
    COMERCIO(9),
    MEIO_AMBIENTE(10),
    CONSULTORIA(11),
    RH(12),
    OUTROS(13);

    private final int valor;

    AreaAtuacaoEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static AreaAtuacaoEnum fromValor(int valor) {
        for (AreaAtuacaoEnum tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        System.err.println("Valor inválido: " + valor);
        return null;
    }
}
