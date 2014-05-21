package org.plos.namedentity.api;

public class NedException extends RuntimeException {

    public NedException(String message) {
        super(message);
    }

    public NedException(String message, Throwable cause) {
        super(message, cause);
    }
}
