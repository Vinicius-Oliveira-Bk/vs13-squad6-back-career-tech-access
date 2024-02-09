package br.com.dbc.vemser.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PropertieReader {
    @Value("${ambiente}")
    private String ambiente;

    @Value("${usuario}")
    private String usuario;

}

