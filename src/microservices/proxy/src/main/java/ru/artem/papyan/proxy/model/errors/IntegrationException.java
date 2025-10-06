package ru.artem.papyan.proxy.model.errors;

public class IntegrationException extends RuntimeException {
    public IntegrationException(String message) {
        super(message);
    }

    public IntegrationException(Throwable cause) {
        super(cause);
    }
}
