package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AREA_ATUACAO")
public class AreaAtuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AREA_ATUACAO")
    @SequenceGenerator(name = "SEQ_AREA_ATUACAO", sequenceName = "SEQ_AREA_ATUACAO", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "interesse")
    @Enumerated(EnumType.ORDINAL)
    private AreasDeInteresse interesse;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_profissional", referencedColumnName = "id")
    private ProfissionalMentor profissionalMentor;
}