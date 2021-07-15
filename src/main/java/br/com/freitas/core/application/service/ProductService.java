package br.com.freitas.core.application.service;

import br.com.freitas.core.application.port.elastic.ElasticHighLevelServicePort;
import br.com.freitas.core.application.port.web.ProductServicePort;
import br.com.freitas.core.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Slf4j
@Singleton
public class ProductService implements ProductServicePort {

    @Inject
    private ElasticHighLevelServicePort elasticService;

    @Override
    public Product saveProduct(Product product) {
        return this.elasticService.post(product);
    }

    @Override
    public Product getProductById(String id) {
        return this.elasticService.get(id);
    }

    @Override
    public Product updateProductById(String id, Product product) {
        return this.elasticService.put(id, product);
    }

    @Override
    public void deleteProductById(String id) {
        this.elasticService.delete(id);
    }

    @Override
    public void existProduct(String id) {
        this.elasticService.head(id);
    }

    @Override
    public List<Product> searchProductByQuery(Product product) {
        var result = this.elasticService.search(product);

        return result.orElse(Collections.emptyList());
    }
}