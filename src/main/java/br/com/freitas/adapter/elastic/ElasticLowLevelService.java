package br.com.freitas.adapter.elastic;

import br.com.freitas.adapter.elastic.mapper.ElasticMapper;
import br.com.freitas.config.RestClientFactory;
import br.com.freitas.core.application.port.elastic.ElasticLowLevelServicePort;
import br.com.freitas.core.domain.Product;
import br.com.freitas.core.exception.InternalServerErrorException;
import br.com.freitas.core.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Slf4j
@Singleton
public class ElasticLowLevelService implements ElasticLowLevelServicePort {

    public static final String ENDPOINT = "products/_doc/";

    private final RestClient client = new RestClientFactory().builderLowLevel();

    @Inject
    private ElasticMapper mapper;

    @Override
    public Product post(String id, Product product) {
        return null;
    }

    @Override
    public Product get(String id) {
        var req = new Request("GET", ENDPOINT + id);

        try {
            var response = this.client.performRequest(req);
            var responseBody = EntityUtils.toString(response.getEntity());

            return this.mapper.toDomain(responseBody);
        } catch (ResponseException e) {
            log.error("Document not found in index: {} of id: {}", ENDPOINT, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error fetching document from index: {}", ENDPOINT, e);
            throw new InternalServerErrorException("Error fetching document!", e);
        }
    }

    @Override
    public Product put(String id, Product product) {
        return null;
    }

    @Override
    public void delete(String id) {
    }
}