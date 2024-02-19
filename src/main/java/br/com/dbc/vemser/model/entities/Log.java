package br.com.dbc.vemser.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "logs")
public class Log {

    @Id
    private String id;
    private LocalDate data;
    private String statusCode;
    private String mensagem;
    private String servico;
}
