package br.com.freitas.adapter.elastic;

import br.com.freitas.core.application.port.elastic.ElasticLowLevelServicePort;
import br.com.freitas.core.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class ElasticLowLevelService implements ElasticLowLevelServicePort {

    public static final String ENDPOINT = "products/_doc/";

    @Inject
    private RestClient client;

    @Override
    public Product post(String id, Product product) {
        return null;
    }

    @Override
    public Product get(String id) {
        return null;
    }

    @Override
    public Product put(String id, Product product) {
        return null;
    }

    @Override
    public void delete(String id) {
    }
}