package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Character acessoPcd;
    private TipoUsuarioEnum tipoUsuario;
    private String interesses;
    private String imagemDocumento;
}
