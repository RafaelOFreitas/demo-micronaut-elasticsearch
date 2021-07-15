package br.com.freitas.adapter.elastic;

import br.com.freitas.adapter.elastic.config.RestClientFactory;
import br.com.freitas.adapter.elastic.mapper.ElasticMapper;
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
import java.util.List;
import java.util.Optional;

import static br.com.freitas.adapter.elastic.constants.ElasticConstants.*;

@Slf4j
@Singleton
public class ElasticLowLevelService implements ElasticLowLevelServicePort {

    private final RestClient client = new RestClientFactory().builderLowLevel();

    @Inject
    private ElasticMapper mapper;

    @Override
    public Product post(Product product) {
        var endpoint = INDEX + DOC;
        var request = new Request(METHOD_POST, endpoint);
        request.setJsonEntity(this.mapper.toJson(product));

        try {
            var response = this.client.performRequest(request);
            var status = response.getStatusLine();

            if (STATUS_CREATED != status.getStatusCode()) {
                log.error("Could not add document to index: {}", INDEX);
                throw new InternalServerErrorException("Error adding document to index: " + INDEX);
            }

            var responseBody = EntityUtils.toString(response.getEntity());
            product.setId(this.mapper.toId(responseBody));

            return product;
        } catch (IOException e) {
            log.error("Error adding document to index: {}", INDEX, e);
            throw new InternalServerErrorException("Error adding document!", e);
        }
    }

    @Override
    public Product get(String id) {
        var endpoint = INDEX + DOC + id;
        var request = new Request(METHOD_GET, endpoint);

        try {
            var response = this.client.performRequest(request);
            var responseBody = EntityUtils.toString(response.getEntity());

            return this.mapper.toDomain(responseBody);
        } catch (ResponseException e) {
            log.error("Document not found in index: {} of id: {}", INDEX, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error fetching document from index: {}", INDEX, e);
            throw new InternalServerErrorException("Error fetching document!", e);
        }
    }

    @Override
    public Product put(String id, Product product) {
        var endpoint = INDEX + UPDATE + id;
        var request = new Request(METHOD_POST, endpoint);
        request.setJsonEntity(String.format("{\"doc\":%s}}", this.mapper.toJson(product)));

        try {
            this.client.performRequest(request);
            return this.get(id);
        } catch (ResponseException e) {
            log.error("Error updating, document not found in index: {} of id: {}", INDEX, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error updating document in index: {}", INDEX, e);
            throw new InternalServerErrorException("Error updating document!", e);
        }
    }

    @Override
    public void delete(String id) {
        var endpoint = INDEX + DOC + id;
        var request = new Request(METHOD_DELETE, endpoint);

        try {
            this.client.performRequest(request);
        } catch (ResponseException e) {
            log.error("Document not found in index: {} of id: {}", INDEX, id, e);
            throw new NotFoundException(e);
        } catch (IOException e) {
            log.error("Error deleting document in index: {}", INDEX, e);
            throw new InternalServerErrorException("Error deleting document!", e);
        }
    }

    @Override
    public boolean head(String id) {
        var request = new Request(METHOD_HEAD, INDEX + DOC + id);

        try {
            var response = this.client.performRequest(request);
            var status = response.getStatusLine();

            if (STATUS_OK != status.getStatusCode()) {
                log.error("Head, document not found in index: {} of id: {}", INDEX, id);
                throw new NotFoundException(String.format("Document not found in index: %s of id: %s", INDEX, id));
            }

            return true;
        } catch (IOException e) {
            log.error("Error fetching document from index: {}", INDEX, e);
            throw new InternalServerErrorException("Error fetching document!", e);
        }
    }

    @Override
    public Optional<List<Product>> search(Product product) {
        return Optional.empty();
    }
}