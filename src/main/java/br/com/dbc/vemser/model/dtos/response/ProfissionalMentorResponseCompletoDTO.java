package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.Data;

@Data
public class ProfissionalMentorResponseCompletoDTO {
    private Long idProfissionalMentor;
    private UsuarioResponseCompletoDTO usuario;
    private AreasDeInteresse areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
