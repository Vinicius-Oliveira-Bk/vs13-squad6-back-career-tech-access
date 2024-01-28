package br.com.dbc.vemser.model.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendarEmailDTO {
    private String nome;
    private String nomeProfissional;
    private String emailCliente;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
}
