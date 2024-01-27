package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalMentorResponseCompletoDTO {
    private Long idProfissionalMentor;
    private UsuarioResponseCompletoDTO usuario;
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
