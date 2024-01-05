package enums;

public enum TipoEnum {
    RESIDENCIAL(1),
    COMERCIAL(2);

    private final int tipo;

    TipoEnum(int tipoEndereco) {
        this.tipo = tipoEndereco;
    }

    public int getValor() {
        return tipo;
    }

    public long getId() {
        return tipo;
    }
}
