package br.com.dbc.vemser.interfaces;

import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;

public interface IVinculosUsuario {
    boolean vincularEndereco(Usuario usuario, Endereco endereco);
    boolean vincularContato(Usuario usuario, Contato contato);
}
