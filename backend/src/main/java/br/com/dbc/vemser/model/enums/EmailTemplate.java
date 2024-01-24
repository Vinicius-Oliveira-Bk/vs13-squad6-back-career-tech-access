package br.com.dbc.vemser.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmailTemplate {
    CRIAR_USUARIO("Usuário criado com sucesso",
            "Seu usuário foi criado com sucesso, muito obrigado por escolherem nós como app!",
            "email-template.html");

    private String tituloEmail;
    private String mensagemTemplate;
    private String arquivo;

}
