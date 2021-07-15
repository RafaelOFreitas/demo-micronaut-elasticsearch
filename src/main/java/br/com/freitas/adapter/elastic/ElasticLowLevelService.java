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
        var req = new Request("POST", ENDPOINT + id);
        req.setJsonEntity(product.toJson());

        try {
            var response = this.client.performRequest(req);
            var status = response.getStatusLine();

            if (201 != status.getStatusCode()) {
                log.error("Could not add document to endpoint: {}", ENDPOINT);
                throw new InternalServerErrorException("Error adding document to endpoint: " + ENDPOINT);
            }

            return product;
        } catch (IOException e) {
            log.error("Error adding document to endpoint: {}", ENDPOINT, e);
            throw new InternalServerErrorException("Error adding document!", e);
        }
    }

    @Override
    public Product get(String id) {
        var req = new Request("GET", ENDPOINT + id);

        try {
            var response = this.client.performRequest(req);
            var responseBody = EntityUtils.toString(response.getEntity());

            return this.mapper.toDomain(responseBody);
        } catch (ResponseException e) {
            log.error("Document not found in endpoint: {} of id: {}", ENDPOINT, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error fetching document from endpoint: {}", ENDPOINT, e);
            throw new InternalServerErrorException("Error fetching document!", e);
        }
    }

    @Override
    public Product put(String id, Product product) {
        var req = new Request("POST", ENDPOINT + id);
        req.setJsonEntity(product.toJson());

        try {
            this.client.performRequest(req);
            return this.get(id);
        } catch (ResponseException e) {
            log.error("Error updating, document not found in endpoint: {} of id: {}", ENDPOINT, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error updating document in endpoint: {}", ENDPOINT, e);
            throw new InternalServerErrorException("Error updating document!", e);
        }
    }

    @Override
    public void delete(String id) {
        var req = new Request("DELETE", ENDPOINT + id);

        try {
            this.client.performRequest(req);
        } catch (ResponseException e) {
            log.error("Document not found in endpoint: {} of id: {}", ENDPOINT, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error deleting document in endpoint: {}", ENDPOINT, e);
            throw new InternalServerErrorException("Error deleting document!", e);
        }
    }

    @Override
    public boolean head(String id) {
        var req = new Request("HEAD", ENDPOINT + id);

        try {
            var response = this.client.performRequest(req);
            var status = response.getStatusLine();

            if (200 != status.getStatusCode()) {
                log.error("Could not add document to endpoint: {}", ENDPOINT);
                return false;
            }

            return true;
        } catch (IOException e) {
            log.error("Error fetching document from endpoint: {}", ENDPOINT, e);
            throw new InternalServerErrorException("Error fetching document!", e);
        }
    }
}