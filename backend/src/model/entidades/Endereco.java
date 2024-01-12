package model.entidades;

import model.enums.TipoEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class Endereco {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private TipoEnum tipo;

    public Endereco() {
        this.id = counter.incrementAndGet();
    }

    public Endereco(String logradouro, String numero, String complemento, String cep, String cidade, String estado, String pais, TipoEnum tipo) {
        this.id = counter.incrementAndGet();
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n\tEndereço Id ...........: " + this.id +
               "\n\tLogradouro ............: " + this.logradouro +
               "\n\tNumero ................: " + this.numero +
               "\n\tComplemento ...........: " + this.complemento +
               "\n\tCep ...................: " + this.cep +
               "\n\tCidade ................: " + this.cidade +
               "\n\tEstado ................: " + this.estado +
               "\n\tPais ..................: " + this.pais +
               "\n\tTipo de endereco ......: " + this.tipo;
    }
}
