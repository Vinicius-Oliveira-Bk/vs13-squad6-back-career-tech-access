package br.com.dbc.vemser.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoDTO {

    @Id
    private String id;
    private LocalDateTime diaHora;
    private String mensagem;
    private Integer quantidade;
}
