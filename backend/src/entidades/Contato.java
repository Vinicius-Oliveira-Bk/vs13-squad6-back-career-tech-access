package entidades;

import enums.TipoEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class Contato {
    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String descricao;
    private String telefone;
    private TipoEnum tipo;

    public Contato() {
        this.id = count.incrementAndGet();
    }

    public Contato(String descricao, String telefone, TipoEnum tipo) {
        this.id = count.incrementAndGet();
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public long getId() { return id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public TipoEnum getTipo() { return tipo; }
    public void setTipo(TipoEnum tipo) { this.tipo = tipo; }


    @Override
    public String toString() {
        String texto = "Id: "+this.id+"\n{";
        texto += "\n\tDescrição: "+this.descricao;
        texto += "\n\tTelefone: "+this.telefone;
        texto += "\n\tTipo: "+this.tipo;
        texto += "\n}";
        return texto;
    }
}
