package br.com.dbc.vemser.model.dtos.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class UsuarioResponseCompletoDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String senha;
    private Character ehPcd;
    private boolean ativo;
    private String tipoDeficiencia;
    private String certificadoDeficienciaGov;
    private String imagemDocumento;
    private List<ContatoResponseDTO> contatos;
    private List<EnderecoResponseDTO> enderecos;
    private Set<CargoResponseDTO> cargos;
}
