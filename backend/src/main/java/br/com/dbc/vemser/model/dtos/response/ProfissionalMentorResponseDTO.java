package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalMentorResponseDTO {
    private Long idProfissionalMentor;
    private UsuarioResponseDTO usuario;
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
