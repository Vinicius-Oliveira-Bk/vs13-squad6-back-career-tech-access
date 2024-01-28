package br.com.dbc.vemser.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pcd extends Cliente {

    private Cliente cliente;
    private Long id;
    private String tipoDeficiencia;
    private String certificadoDeficienciaGov;
}
