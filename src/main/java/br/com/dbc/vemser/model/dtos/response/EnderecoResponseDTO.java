package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.TipoEnum;
import lombok.Data;

@Data
public class    EnderecoResponseDTO {
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private TipoEnum tipo;
}