package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.AreasDeInteresse;
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
@Entity(name = "AREA_INTERESSE")
public class AreaInteresse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AREA_INTERESSE")
    @SequenceGenerator(name = "SEQ_AREA_INTERESSE", sequenceName = "SEQ_AREA_INTERESSE", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "interesse")
    @Enumerated(EnumType.ORDINAL)
    private AreasDeInteresse interesse;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;
}