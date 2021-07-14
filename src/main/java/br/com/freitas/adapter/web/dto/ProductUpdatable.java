package br.com.freitas.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Introspected
public class ProductUpdatable {

    private String name;

    private Integer amount;

    private BigDecimal price;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("expiration_date")
    private String expirationDate;
}
