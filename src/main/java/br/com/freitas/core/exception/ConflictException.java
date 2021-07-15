package br.com.freitas.core.exception;

public class ConflictException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(Throwable throwable) {
        super("Document already exists!", throwable);
    }
}