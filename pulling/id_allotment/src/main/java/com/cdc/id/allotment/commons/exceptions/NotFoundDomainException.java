package com.cdc.id.allotment.commons.exceptions;

public class NotFoundDomainException extends DomainException{
    public NotFoundDomainException(String message) {
        super(message);
    }

    public NotFoundDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundDomainException(String messageFormat, Object... args) {
        super(messageFormat, args);
    }
}
