package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClienteRequestDTO {
    @NotNull
    @Schema(description = "Tipo de plano GRATUITO, BASICO, PREMIUM", required = true, example = "GRATUITO")
    private PlanoEnum tipoPlano;
    @NotNull
    @Schema(description = "Informar se possui controle parental", required = true, example = "S")
    private Character controleParental;
    @NotNull
    @Schema(description = "Informar se é um estudante", required = true, example = "N")
    private Character ehEstudante;
    @NotNull
    @Schema(description = "Informar se é um profissional em realocação", required = true, example = "S")
    private Character ehProfissionalRealocacao;

    @Schema(description = "Informar a profissão", required = true, example = "Professor de Matemática")
    @Size(max = 255)
    private String profissao;
    @Schema(description = "Informar o objetivo profissional", required = true, example = "Auxiliar as pessoas")
    @Size(max = 255)
    private String objetivoProfissional;
    //estudante
    @Schema(description = "Informar a matrícula", required = true, example = "321654987")
    @Size(max = 20)
    private String matricula;
    @Schema(description = "Informar comprovante de matrícula", required = true, example = "http://comprovantematricula.com.br")
    @Size(max = 255)
    private String comprovanteMatricula;
    @Schema(description = "Informar Instituição de ensino", required = true, example = "UEM - Universidade Estadual de Maringá")
    @Size(max = 255)
    private String instituicao;
    @Schema(description = "Informar Curso", required = true, example = "Bacharelado em Matemática")
    @Size(max = 255)
    private String curso;
    @Schema(description = "Informar interesses", required = true, example = "TI")
    private List<AreaInteresse> interesses;
    @Schema(description = "Informar data de início do curso (yyyy-mm-dd)", required = true, example = "2022-06-01")
    @Past
    private LocalDate dataInicio;

    @Schema(description = "Informar data de previsão de conclusão do curso (yyyy-mm-dd)", required = true, example = "2023-06-01")
    private LocalDate dataTermino;
}
