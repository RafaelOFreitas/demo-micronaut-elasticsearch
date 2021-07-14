package br.com.freitas.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Introspected
public class ProductInput {

    @NotBlank
    private String name;

    private Integer amount = 1;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("expiration_date")
    private String expirationDate;
}
