package com.leonardoguedex.pedidos.exception;

public class DataIntegrationException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrationException(String message) {
        super(message);
    }

    public DataIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
