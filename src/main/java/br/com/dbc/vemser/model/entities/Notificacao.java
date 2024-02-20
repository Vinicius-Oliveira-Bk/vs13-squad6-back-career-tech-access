package br.com.dbc.vemser.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "notificacoes")
public class Notificacao {

    @Id
    private String id;
    private LocalDateTime diaHora;
    private String mensagem;
    private Integer quantidade;
}
