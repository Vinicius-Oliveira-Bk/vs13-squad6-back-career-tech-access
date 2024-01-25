package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioResponseCompletoDTO extends UsuarioRequestDTO {
    private Long id;
    private List<Contato> contatos;
    private List<Endereco> enderecos;
}
