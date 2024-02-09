package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENTE")
public class Cliente {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @Enumerated(EnumType.ORDINAL)
    private List<AreaInteresse> interesses;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Agenda> agendas;
}