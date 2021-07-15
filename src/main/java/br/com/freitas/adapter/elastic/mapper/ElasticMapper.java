package br.com.freitas.adapter.elastic.mapper;

import br.com.freitas.core.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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

    public Product toDomain(String entity) {
        var map = new Gson().fromJson(entity, Map.class);

        var resp = map.get("_source");

        return this.toDomain((Map<String, Object>) resp);
    }
}
