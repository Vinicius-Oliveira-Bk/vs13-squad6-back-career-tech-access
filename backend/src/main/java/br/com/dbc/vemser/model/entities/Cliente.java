package br.com.dbc.vemser.model.entities;

import java.time.LocalDate;

import br.com.dbc.vemser.interfaces.IDocumentacaoPessoal;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario implements IDocumentacaoPessoal {
    private Long id;
    private Usuario usuario;
    private PlanoEnum tipoPlano;
    private Character controleParental;

    @Override
    public boolean validarCPF(String cpf) {
        String cpfNumerico = cpf.replaceAll("[^\\d]", "");

        if (cpfNumerico.length() == 11) {
            return true;
        } else {
            System.err.println("Erro: CPF deve conter exatamente 11 dígitos.");
            return false;
        }
    }

    @Override
    public boolean validarIdade(LocalDate dataNascimento, boolean cadastroResponsavel) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataDezoitoAnosAtras = dataAtual.minusYears(18);

        if (!dataNascimento.isAfter(dataDezoitoAnosAtras)) {
            return true;
        } else {
            if (!cadastroResponsavel) {
                // Realizar o cadastro do responsável
                System.err.println("É necessário cadastrar o responsável antes de prosseguir.");
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean validarPlano(String plano) {
        return plano != null && !plano.isEmpty();
    }

    @Override
    public boolean validarInteresses(String interesses) {
        return interesses != null && !interesses.isEmpty();
    }

    @Override
    public boolean validarImagemDocumento(String imagemDocumento) {
        return imagemDocumento != null && !imagemDocumento.isEmpty();
    }

    @Override
    public Character validarControleParental(boolean controleParental) {
        return null;
    }

    @Override
    public Character validarAcessoPcd(boolean acessoPcd) {
        return null;
    }

    @Override
    public Character validarControleParental(Character controleParental) {
        return controleParental;
    }

    @Override
    public Character validarAcessoPcd(Character acessoPcd) {
        return acessoPcd;
    }

}