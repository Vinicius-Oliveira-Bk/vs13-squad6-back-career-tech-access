package br.com.dbc.vemser.mappers;

import br.com.dbc.vemser.model.dtos.response.RelatorioEnderecoDTO;
import br.com.dbc.vemser.model.entities.Endereco;

public class EnderecoMapper {
    public static RelatorioEnderecoDTO enderecoToRelatorioEnderecoDTO(Endereco endereco) {
        RelatorioEnderecoDTO relatorioEnderecoDTO = new RelatorioEnderecoDTO();

        relatorioEnderecoDTO.setCep(endereco.getCep());
        relatorioEnderecoDTO.setCidade(endereco.getCidade());
        relatorioEnderecoDTO.setComplemento(endereco.getComplemento());
        relatorioEnderecoDTO.setEstado(endereco.getEstado());
        relatorioEnderecoDTO.setLogradouro(endereco.getLogradouro());
        relatorioEnderecoDTO.setNumero(endereco.getNumero());
        relatorioEnderecoDTO.setPais(endereco.getPais());
        relatorioEnderecoDTO.setTipoEndereco(endereco.getTipo());

        return relatorioEnderecoDTO;
    }
}
