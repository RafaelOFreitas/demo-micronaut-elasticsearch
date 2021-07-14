package br.com.freitas.adapter.web.handler;

import br.com.freitas.core.exception.BadRequestException;
import br.com.freitas.core.exception.BaseException;
import br.com.freitas.core.exception.InternalServerErrorException;
import br.com.freitas.core.exception.NotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Produces
@Singleton
@Requires(classes = {BaseException.class, ExceptionHandler.class})
public class BaseExceptionHandler implements ExceptionHandler<BaseException, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, BaseException exception) {
        if (exception instanceof NotFoundException) {
            var status = HttpStatus.NOT_FOUND;
            var errorType = ErrorType.RESOURCE_NOT_FOUND;
            String detail = exception.getMessage();

            var error = this.createError(status, errorType, detail);

            return HttpResponse.status(status).body(error);
        }

        if (exception instanceof InternalServerErrorException) {
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            var errorType = ErrorType.INTERNAL_SERVER_ERROR;
            String detail = exception.getMessage();

            var error = this.createError(status, errorType, detail);

            return HttpResponse.status(status).body(error);
        }

        if (exception instanceof BadRequestException) {
            var status = HttpStatus.BAD_REQUEST;
            var errorType = ErrorType.BAD_REQUEST;
            String detail = exception.getMessage();

            var error = this.createError(status, errorType, detail);

            return HttpResponse.status(status).body(error);
        }

        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }

    private Error createError(HttpStatus status, ErrorType type, String detail) {
        return Error.builder()
                .timestamp(DateTimeFormatter.ISO_DATE_TIME.format(OffsetDateTime.now()))
                .status(status.getCode())
                .title(type.getTitle())
                .detail(detail)
                .userMessage(detail)
                .build();
    }
}