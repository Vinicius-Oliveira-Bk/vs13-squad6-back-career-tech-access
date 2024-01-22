package br.com.dbc.vemser.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudante extends Cliente {

    private Cliente cliente;
    private Long id;
    private String matricula;
    private String comprovanteMatricula;
    private String instituicao;
    private String curso;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
}