package br.com.freitas.core.exception;

public class BadRequestException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }
}