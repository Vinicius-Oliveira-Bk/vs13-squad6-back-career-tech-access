package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.AreaAtuacao;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.Data;

import java.util.List;

@Data
public class ProfissionalMentorResponseCompletoDTO {
    private Long idProfissionalMentor;
    private UsuarioResponseCompletoDTO usuario;
    private List<AreaAtuacao> atuacoes;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
