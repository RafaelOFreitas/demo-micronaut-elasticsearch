package br.com.freitas.core.application.port.elastic;

import br.com.freitas.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ElasticHighLevelServicePort {

    Product post(String id, Product product);

    Product get(String id);

    Product put(String id, Product product);

    void delete(String id);

    boolean head(String id);

    Optional<List<Product>> search(String query);
}
