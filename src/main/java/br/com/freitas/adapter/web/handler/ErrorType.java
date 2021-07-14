package br.com.freitas.adapter.web.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    BAD_REQUEST("Bad Request"),
    RESOURCE_NOT_FOUND("Resource Not Found"),
    INTERNAL_SERVER_ERROR("System Error");

    private final String title;
}
