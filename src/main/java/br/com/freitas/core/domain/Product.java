package br.com.freitas.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private String id;

    private String name;

    private Integer amount;

    private BigDecimal price;

    private String description;

    private String expirationDate;
}
