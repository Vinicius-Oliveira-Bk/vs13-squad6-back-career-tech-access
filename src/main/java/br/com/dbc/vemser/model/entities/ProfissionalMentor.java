package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PROFISSIONAL_MENTOR")
public class ProfissionalMentor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROFISSIONAL_MENTOR")
    @SequenceGenerator(name = "SEQ_PROFISSIONAL_MENTOR", sequenceName = "SEQ_PROFISSIONAL_MENTOR", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "nivel_experiencia")
    private NivelExperienciaEnum nivelExperienciaEnum;
    @Column(name = "carteira_trabalho")
    private String carteiraDeTrabalho;
    @Column(name = "ativo")
    private boolean isAtivo;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profissionalMentor")
    private List<Agenda> agendas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profissionalMentor", cascade = CascadeType.ALL)
    @Enumerated(EnumType.ORDINAL)
    private List<AreaAtuacao> atuacoes;
}
