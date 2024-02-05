package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RelatorioAgendaMentorDTO {

    @Schema(description = "Nome do mentor", example = "João da Silva")
    private String nome;

    @Schema(description = "Email do mentor", example = "email@email.com")
    private String email;

    @Schema(description = "Se o mentor é pessoa com deficiência", example = "S ou N")
    private Character ehPCD;

    @Schema(description = "Tipo de deficiência do mentor", example = "Física")
    private String tipoDeficiencia;

    @Schema(description = "Certificado de deficiência do governo", example = "123456")
    private String certificadoDeficienciaGoverno;

    @Schema(description = "Imagem do documento do mentor", example = "imagem.jpg")
    private String imagem;

    @Schema(description = "Nível de experiência do mentor", example = "JUNIOR")
    private NivelExperienciaEnum nivelExperiencia;

    @Schema(description = "Carteira de trabalho do mentor", example = "123456")
    private String carteiraDeTrabalho;

    @Schema(description = "Áreas de atuação do mentor", example = "DESENVOLVIMENTO")
    private Set<String> areaDeAtuacao = new HashSet<>();

}
