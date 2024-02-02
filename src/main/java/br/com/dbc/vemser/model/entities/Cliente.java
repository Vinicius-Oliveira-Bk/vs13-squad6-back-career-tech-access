package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.interfaces.IDocumentacaoPessoal;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENTE")
public class Cliente implements IDocumentacaoPessoal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
    @SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_plano")
    private PlanoEnum tipoPlano;
    @Column(name = "controle_parental")
    private Character controleParental;
    @Column(name = "eh_estudante")
    private Character ehEstudante;
    @Column(name = "eh_profissional_realocacao")
    private Character ehProfissionalRealocacao;
    //profissionalRealocacao
    @Column(name = "profissao")
    private String profissao;
    @Column(name = "objetivo_profissional")
    private String objetivoProfissional;
    //estudante
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "comprovante_matricula")
    private String comprovanteMatricula;
    @Column(name = "instituicao")
    private String instituicao;
    @Column(name = "curso")
    private String curso;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_termino")
    private LocalDate dataTermino;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @Enumerated(EnumType.ORDINAL)
    private List<AreaInteresse> interesses;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Agenda> agendas;
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