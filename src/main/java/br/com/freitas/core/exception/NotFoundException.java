package br.com.freitas.core.exception;

public class NotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable throwable) {
        super("Document not found!", throwable);
    }
}