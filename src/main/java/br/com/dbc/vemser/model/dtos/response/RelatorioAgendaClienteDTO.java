package br.com.dbc.vemser.model.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RelatorioAgendaClienteDTO {

    @Schema(description = "Nome do cliente", example = "João da Silva")
    private String nome;

    @Schema(description = "Email do cliente", example = "email@email.com")
    private String email;

    @Schema(description = "Se o cliente é pcd ou não", example = "S ou N")
    private Character ehPCD;

    @Schema(description = "Se o cliente é estudante ou não", example = "S ou N")
    private Character ehEstudante;

    @Schema(description = "Se o cliente é profissional de realocação ou não", example = "S ou N")
    private Character ehProfissionalRealocacao;

    @Schema(description = "Tipo da deficiência do cliente", example = "FÍSICA")
    private String tipoDeficiencia;

    @Schema(description = "Certificado de deficiência do governo", example = "123456")
    private String certificadoDeficienciaGoverno;

    @Schema(description = "Imagem do documento do cliente", example = "imagem.jpg")
    private String imagem;

    @Schema(description = "Contatos do cliente", example = "Contatos do cliente")
    private Set<RelatorioContatoDTO> contatos = new HashSet<>();

    @Schema(description = "Endereços do cliente", example = "Endereços do cliente")
    private Set<RelatorioEnderecoDTO> enderecos = new HashSet<>();

    @Schema(description = "Áreas de interesse do cliente", example = "DESENVOLVIMENTO")
    private Set<String> interesses = new HashSet<>();

}
