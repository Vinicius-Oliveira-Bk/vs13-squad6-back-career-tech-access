package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ClienteResponseDTO {
    private Long id;
    private UsuarioResponseDTO usuario;
    private PlanoEnum tipoPlano;
    private Character controleParental;
    private Character ehEstudante;
    private Character ehProfissionalRealocacao;
    private String profissao;
    private String objetivoProfissional;
    private String matricula;
    private String comprovanteMatricula;
    private String instituicao;
    private String curso;
    private List<AreaInteresse> interesses;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
}
