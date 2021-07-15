package br.com.freitas.core.application.port.elastic;

import br.com.freitas.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ElasticHighLevelServicePort {

    Product post(Product product);

    Product get(String id);

    Product put(String id, Product product);

    void delete(String id);

    void head(String id);

    Optional<List<Product>> search(Product product);
}
