package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String EMAIL_DESTINATARIO = "email";

    private final JavaMailSender emailSender;

    private final Configuration fmConfiguration;

    private final ObjectMapper objectMapper;

    @Value("${spring.mail.username}")
    private String emailRemetente;

    public void sendEmail(Object objetoEmail, EmailTemplate emailTemplate) throws RegraDeNegocioException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            Map<String, Object> informacoesEmail = objectMapper.convertValue(objetoEmail, HashMap.class);

            String emailDestinatario = informacoesEmail.get(EMAIL_DESTINATARIO).toString();

            mimeMessageHelper.setFrom(emailRemetente);
            mimeMessageHelper.setTo(emailDestinatario);
            mimeMessageHelper.setSubject(emailTemplate.getTituloEmail());

            String email = montarTemplate(informacoesEmail, emailTemplate);

            mimeMessageHelper.setText(email, true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception e) {
            throw new RegraDeNegocioException("Não foi possível enviar o e-mail");
        }
    }

    private String montarTemplate(Map<String, Object> informacoesEmail, EmailTemplate emailTemplate) throws IOException, TemplateException {
        Template template = fmConfiguration.getTemplate(emailTemplate.getArquivo());

        informacoesEmail.put("mensagem", mensagemReplace(emailTemplate.getMensagemTemplate(), informacoesEmail));
        informacoesEmail.put("tituloEmail", emailTemplate.getTituloEmail());

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, informacoesEmail);
    }

    private String mensagemReplace(String mensagemTemplate, Map<String, Object> informacoesEmail) {
        String mensagemRetorno = mensagemTemplate;

        for (String key : informacoesEmail.keySet()) {
            if (informacoesEmail.get(key) != null) {
                mensagemRetorno = mensagemRetorno.replace("${" + key + "}", informacoesEmail.get(key).toString());
            }
        }

        return mensagemRetorno;
    }

}
