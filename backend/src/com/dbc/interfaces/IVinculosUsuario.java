package com.dbc.interfaces;

import com.dbc.model.entities.Contato;
import com.dbc.model.entities.Endereco;
import com.dbc.model.entities.Usuario;

import java.util.ArrayList;

public interface IVinculosUsuario {
    boolean vincularEndereco(Usuario usuario, Endereco endereco);
    boolean vincularContato(Usuario usuario, Contato contato);
}
