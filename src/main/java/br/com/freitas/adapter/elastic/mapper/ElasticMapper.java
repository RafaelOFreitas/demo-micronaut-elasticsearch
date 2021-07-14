package br.com.freitas.adapter.elastic.mapper;

import br.com.freitas.core.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class ElasticMapper {

    @Inject
    private ObjectMapper objectMapper;

    public Product toDomain(Map<String, Object> response) {
        return this.objectMapper.convertValue(response, Product.class);
    }
}
