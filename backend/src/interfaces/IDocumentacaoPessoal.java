package interfaces;

import java.time.LocalDate;

public interface IDocumentacaoPessoal {
    public boolean validarCPF(String cpf);
    public boolean validarIdade(LocalDate dataNascimento);
    public boolean validarPlano(String plano);
    public boolean validarInteresses(String interesses);
    public boolean validarImagemDocumento(String imagemDocumento);
    public boolean validarControleParental(boolean controleParental);
    public boolean validarAcessoPcd(boolean acessoPcd);
}