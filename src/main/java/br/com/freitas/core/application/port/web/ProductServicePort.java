package br.com.freitas.core.application.port.web;

import br.com.freitas.core.domain.Product;

import java.util.List;

public interface ProductServicePort {

    Product saveProduct(Product product);

    Product getProductById(String id);

    Product updateProductById(String id, Product product);

    void deleteProductById(String id);

    void existProduct(String id);

    List<Product> searchProductByQuery(Product product);
}
