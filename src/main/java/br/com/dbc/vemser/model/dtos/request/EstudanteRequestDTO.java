package br.com.dbc.vemser.model.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class EstudanteRequestDTO {

    private Long id;
    private String matricula;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String comprovanteMatricula;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String instituicao;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String curso;

    @NotNull(message = "A data de início não pode ser nula")
    @Past(message = "A data de início deve ser uma data passada")
    private LocalDate dataInicio;

    @NotNull(message = "A data de término não pode ser nula")
    private LocalDate dataTermino;
}
