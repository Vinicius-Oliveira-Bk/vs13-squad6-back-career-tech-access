package com.dbc.exceptions;

import java.sql.SQLException;

public class BancoDeDadosException extends SQLException {
    public BancoDeDadosException(Throwable cause) {
        super(cause);
    }
}
