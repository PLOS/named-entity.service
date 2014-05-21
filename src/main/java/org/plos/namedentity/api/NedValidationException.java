package org.plos.namedentity.api;

public class NedValidationException extends NedException {

    public NedValidationException(String message) {
        super(message);
    }

    public NedValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
