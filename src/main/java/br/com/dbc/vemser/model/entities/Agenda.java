package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AGENDA")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENDA")
    @SequenceGenerator(name = "SEQ_AGENDA", sequenceName = "SEQ_AGENDA", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "data_inicio")
    private LocalDateTime dataHoraInicio;
    @Column(name = "data_fim")
    private LocalDateTime dataHoraFim;
    @Enumerated(EnumType.ORDINAL)
    private StatusAgendaEnum statusAgendaEnum;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_mentor", referencedColumnName = "id")
    private ProfissionalMentor profissionalMentor;
}
