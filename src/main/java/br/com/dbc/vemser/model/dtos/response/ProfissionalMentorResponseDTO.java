package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.*;

@Data
public class ProfissionalMentorResponseDTO {
    private Long idProfissionalMentor;
    private UsuarioResponseDTO usuario;
    private AreasDeInteresse areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
