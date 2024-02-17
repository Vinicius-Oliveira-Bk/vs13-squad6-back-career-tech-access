package br.com.dbc.vemser.model.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Character ehPcd;
    private String tipoDeficiencia;
    private String certificadoDeficienciaGov;
    private String imagemDocumento;
    private boolean isAtivo;
}
