package com.dbc.interfaces;

import java.time.LocalDate;

public interface IDocumentacaoPessoal {
    public boolean validarCPF(String cpf);
    public boolean validarIdade(LocalDate dataNascimento, boolean cadastroResponsavel);
    public boolean validarPlano(String plano);
    public boolean validarInteresses(String interesses);
    public boolean validarImagemDocumento(String imagemDocumento);
    public Character validarControleParental(boolean controleParental);
    public Character validarAcessoPcd(boolean acessoPcd);

    Character validarControleParental(Character controleParental);

    Character validarAcessoPcd(Character acessoPcd);
}