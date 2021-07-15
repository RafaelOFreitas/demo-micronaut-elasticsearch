package br.com.freitas.core.application.port.elastic;

import br.com.freitas.core.domain.Product;

public interface ElasticHighLevelServicePort {

    Product post(String id, Product product);

    Product get(String id);

    Product put(String id, Product product);

    void delete(String id);

    boolean head(String id);

    // void search(String id);
}
