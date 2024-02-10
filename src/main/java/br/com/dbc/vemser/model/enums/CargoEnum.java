package br.com.dbc.vemser.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CargoEnum {
    ROLE_ADMIN(1),
    ROLE_USUARIO(2),
    ROLE_CLIENTE(3),
    ROLE_PROFISSIONAL(4);

    private final Integer valor;
}
