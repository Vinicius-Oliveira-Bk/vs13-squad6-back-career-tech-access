package br.com.dbc.vemser.model.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfissionalRealocacaoRequestDTO {

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String profissao;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String objetivoProfissional;
}
