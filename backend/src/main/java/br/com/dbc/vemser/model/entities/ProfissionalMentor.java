package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.Data;

@Data
public class ProfissionalMentor extends Usuario {
    private Long idProfissionalMentor;
    private Usuario usuario;
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
