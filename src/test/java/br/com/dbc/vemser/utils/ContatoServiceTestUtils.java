package br.com.dbc.vemser.utils;

import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.enums.TipoEnum;

public class ContatoServiceTestUtils {

    public static Contato createContatoDefault() {
        Contato contato = new Contato();

        contato.setId(1L);
        contato.setTelefone("48999999999");
        contato.setTipo(TipoEnum.COMERCIAL);
        contato.setDescricao("Descrição do contato");
//        contato.setUsuario(UsuarioServiceTestUtils.createUsuarioDefault());

        return contato;
    }

    public static ContatoRequestDTO createContatoRequestDTO() {
        ContatoRequestDTO contatoRequestDTO = new ContatoRequestDTO();

        contatoRequestDTO.setTelefone("48999999999");
        contatoRequestDTO.setTipo(TipoEnum.COMERCIAL);
        contatoRequestDTO.setDescricao("Descrição do contato");

        return contatoRequestDTO;
    }

    public static Contato createContatoComUsuario() {
        Contato contato = new Contato();

        contato.setId(1L);
        contato.setTelefone("48999999999");
        contato.setTipo(TipoEnum.COMERCIAL);
        contato.setDescricao("Descrição do contato");
        contato.setUsuario(UsuarioServiceTestUtils.createUsuarioDefault());

        return contato;
    }

    public static ContatoResponseDTO createContatoResponseDTO() {
        ContatoResponseDTO contatoResponseDTO = new ContatoResponseDTO();

        contatoResponseDTO.setId(1L);
        contatoResponseDTO.setTelefone("48999999999");
        contatoResponseDTO.setTipo(TipoEnum.COMERCIAL);
        contatoResponseDTO.setDescricao("Descrição do contato");

        return contatoResponseDTO;
    }
}
