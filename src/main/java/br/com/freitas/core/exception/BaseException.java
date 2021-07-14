package br.com.freitas.core.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}