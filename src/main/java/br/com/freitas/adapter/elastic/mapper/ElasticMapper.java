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

    public Product toDomain(Map<String, Object> response, String id) {
        var product = this.toDomain(response);
        product.setId(id);
        return product;
    }

    @SuppressWarnings("unchecked")
    public Product toDomain(String entity) {
        var map = new Gson().fromJson(entity, Map.class);

        var source = (Map<String, Object>) map.get("_source");
        var id = String.valueOf(map.get("_id"));

        return this.toDomain(source, id);
    }

    public String toId(String entity) {
        var map = new Gson().fromJson(entity, Map.class);

        return String.valueOf(map.get("_id"));
    }

    public String toJson(Product product) {
        return new Gson().toJson(product);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap(Product product) {
        return new Gson().fromJson(this.toJson(product), Map.class);
    }

    private Product toDomain(Map<String, Object> response) {
        return this.objectMapper.convertValue(response, Product.class);
    }
}
