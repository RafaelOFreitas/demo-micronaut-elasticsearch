package br.com.freitas.adapter.web.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private final Integer status;
    private final String timestamp;
    private final String type;
    private final String title;
    private final String detail;
    private final String userMessage;
    private final List<Field> fields;

    @Getter
    @Builder
    public static class Field {

        private final String name;
        private final String userMessage;
    }
}
