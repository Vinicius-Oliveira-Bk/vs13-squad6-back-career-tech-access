package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.response.RelatorioContatoDTO;
import br.com.dbc.vemser.model.entities.Contato;

public class ContatoMapper {
    public static RelatorioContatoDTO contatoToRelatorioContatoDTO(Contato contato) {
        RelatorioContatoDTO relatorioContatoDTO = new RelatorioContatoDTO();

        relatorioContatoDTO.setTipoContato(contato.getTipo());
        relatorioContatoDTO.setDescricao(contato.getDescricao());
        relatorioContatoDTO.setTelefone(contato.getTelefone());

        return relatorioContatoDTO;
    }
    
}
