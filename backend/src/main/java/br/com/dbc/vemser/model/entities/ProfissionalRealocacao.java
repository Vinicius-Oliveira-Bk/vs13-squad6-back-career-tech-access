package br.com.dbc.vemser.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalRealocacao extends Cliente {

    private Long id;
    private Cliente cliente;
    private String profissao;
    private String objetivoProfissional;
}