package br.com.dbc.vemser.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private String id;
    private LocalDate data;
    private String statusCode;
    private String mensagem;
    private String servico;
}
