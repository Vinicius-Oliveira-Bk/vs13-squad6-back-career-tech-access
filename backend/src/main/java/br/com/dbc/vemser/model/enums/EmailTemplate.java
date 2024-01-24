package br.com.dbc.vemser.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmailTemplate {
    CRIAR_USUARIO("Usuário criado com sucesso", "aaaaaaaaaaaaaaaaaaaaaaa ${email}",
            "email-template.html");

    private String tituloEmail;
    private String mensagemTemplate;
    private String arquivo;

}
