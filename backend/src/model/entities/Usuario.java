package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import enums.TipoUsuarioEnum;
import utils.Utils;

public abstract class Usuario {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private long id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private ArrayList<Endereco> enderecos = new ArrayList<>();
    private ArrayList<Contato> contatos = new ArrayList<>();
    private String email;
    private TipoUsuarioEnum tipo;

    public Usuario() {
        this.id = counter.incrementAndGet();
    }

    public Usuario(String nome, String cpf, LocalDate dataDeNascimento, ArrayList<Endereco> enderecos,
            ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo) {
        this.id = counter.incrementAndGet();
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.enderecos = enderecos;
        this.contatos = contatos;
        this.email = email;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuarioEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String dataDeNascimentoFormatada = Utils.formatarData(dataDeNascimento);

        return "\t******** Usuario ********* " +
               "\n\tId ....................: " + this.id +
               "\n\tNome ..................: " + this.nome +
               "\n\tContatos ..............: " + Arrays.toString(this.contatos.toArray()) +
               "\n\tEnderecos .............: " + Arrays.toString(this.enderecos.toArray()) +
               "\n\tCpf ...................: " + this.cpf +
               "\n\tData de Nascimento ....: " + dataDeNascimentoFormatada +
               "\n\tE-mail ................: " + this.email +
               "\n\tTipo de usuario .......: " + this.tipo;
    }
}
