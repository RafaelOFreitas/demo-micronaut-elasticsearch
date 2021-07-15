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

    @SuppressWarnings("unchecked")
    public Product toDomain(String entity, String source) {
        var map = new Gson().fromJson(entity, Map.class);

        var resp = (Map<String, Object>) map.get(source);

        return this.toDomain(resp);
    }

    @SuppressWarnings("unchecked")
    public Product toDomain(String entity) {
        var map = new Gson().fromJson(entity, Map.class);
        return this.toDomain(map);
    }

    public String toJson(Product product) {
        return new Gson().toJson(product);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap(Product product) {
        return new Gson().fromJson(this.toJson(product), Map.class);
    }
}
