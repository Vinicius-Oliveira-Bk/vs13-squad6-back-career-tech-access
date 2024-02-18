package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.AreaAtuacao;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalMentorResponseDTO {
    private Long id;
    private List<AreaAtuacao> atuacoes;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
}
