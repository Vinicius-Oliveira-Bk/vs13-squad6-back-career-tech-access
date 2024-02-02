package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProfissionalMentorRequestDTO {

    @NotNull(message = "A área de atuação deve ser informada, escolha entre: TI, SAUDE, EDUCACAO, FINANCAS, MARKETING, JURIDICO, ENGENHARIA, DESIGN, COMERCIO, MEIO_AMBIENTE, CONSULTORIA, RH, OUTROS.")
    @Schema(description = "Coleção de áreas de atuação", required = true, example = "[\"TI\", \"SAUDE\"]")
    private List<AreasDeInteresse> atuacoes;

    @NotNull(message = "O nível de experiência deve ser informado, escolha entre: JUNIOR, PLENO OU SENIOR.")
    @Schema(description = "Nível de experiência", required = true, example = "JUNIOR")
    private NivelExperienciaEnum nivelExperienciaEnum;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    @Size(max = 20)
    @Schema(description = "Carteira de Trabalho", required = true, example = "65465498765")
    private String carteiraDeTrabalho;

}
