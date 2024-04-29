package com.cdc.id.allotment.commons.exceptions;

public class DomainException extends RuntimeException{
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
