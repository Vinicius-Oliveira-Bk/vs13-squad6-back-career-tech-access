package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProfissionalMentorRequestDTO {
    @NotNull(message = "A área de atuação deve ser informada, escolha entre: TI, SAUDE, EDUCACAO, FINANCAS, MARKETING, JURIDICO, ENGENHARIA, DESIGN, COMERCIO, MEIO_AMBIENTE, CONSULTORIA, RH, OUTROS.")
    private AreaAtuacaoEnum areaAtuacao;
    @NotNull(message = "O nível de experiência deve ser informado, escolha entre: JUNIOR, PLENO OU SENIOR.")
    private NivelExperienciaEnum nivelExperienciaEnum;
    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String carteiraDeTrabalho;
}
