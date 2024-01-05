package entidades;

import enums.TipoEnum;

public class Endereco {
    private int tipotipoEndereco;

    public Endereco(
            String cep,
            String cidade,
            String complemento,
            String estado,
            String pais,
            String logradouro,
            String numero,
            int tipo){

        this.tipotipoEndereco = tipo;
    }

    public Endereco(TipoEnum tipoEndereco) {
          this.tipotipoEndereco = tipoEndereco.getValor();
    }

    public Endereco(TipoEnum tipoEnum, String ruaTeste, String bairroTeste, String cidadeTeste, String estadoTeste, String paisTeste, String number, int tipo) {
    }

    public static Endereco getEndereco(
            String cep,
            String cidade,
            String complemento,
            String estado,
            String pais,
            String logradouro,
            String numero,
            int tipo) {
        return new Endereco(cep, cidade, complemento, estado, pais, logradouro, numero, tipo);
    }

    public int getTipo() { return tipotipoEndereco; }
}