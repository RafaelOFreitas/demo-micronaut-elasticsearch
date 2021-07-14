package br.com.freitas.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductOutput {

    private String name;

    private Integer amount;

    private BigDecimal price;

    private String description;

    @JsonProperty("expiration_date")
    private String expirationDate;
}
