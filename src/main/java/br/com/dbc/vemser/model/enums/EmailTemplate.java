package br.com.dbc.vemser.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmailTemplate {
    CRIAR_USUARIO("Usuário criado com sucesso",
            "Seu usuário foi criado com sucesso, muito obrigado por escolherem nós como app! " +
                    "<br/ ><br/ > Seu e-mail para uso é: ${email}"),
    AGENDAR_HORARIO("Agendamento realizado com sucesso",
                          "Seu agendamento foi realizado com sucesso, muito obrigado por escolherem nós como app! " +
                          "<br/ ><br/ > Seu horário é: ${dataHoraInicio} até ${dataHoraFim} com o profissional ${nomeProfissional}.");

    private String tituloEmail;
    private String mensagemTemplate;
    private final String arquivo = "email-template.html";

}
