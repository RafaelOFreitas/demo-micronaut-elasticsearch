package br.com.freitas.adapter.web;

import br.com.freitas.adapter.web.dto.ProductInput;
import br.com.freitas.adapter.web.dto.ProductOutput;
import br.com.freitas.adapter.web.dto.ProductQuery;
import br.com.freitas.adapter.web.dto.ProductUpdatable;
import br.com.freitas.adapter.web.mapper.ProductMapper;
import br.com.freitas.core.application.port.web.ProductServicePort;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller("/products")
public class ProductController {

    @Inject
    private ProductServicePort productService;

    @Inject
    private ProductMapper mapper;

    @Post("/{id}")
    @Status(HttpStatus.CREATED)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductOutput post(@NotBlank String id, @Body @Valid ProductInput product) {
        log.info("Add document to index /products/_doc/{}", id);

        var domain = this.mapper.toDomain(product);

        var response = this.productService.saveProductWithId(id, domain);

        return this.mapper.toOutput(response);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductOutput get(@NotBlank String id) {
        log.info("Search for document in index /products/_doc/{}", id);

        var response = this.productService.getProductById(id);

        return this.mapper.toOutput(response);
    }

    @Put("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductOutput put(@NotBlank String id, @Body @Valid ProductUpdatable product) {
        log.info("Update document in index /products/_doc/{}", id);

        var domain = this.mapper.toDomain(product);

        var response = this.productService.updateProductById(id, domain);

        return this.mapper.toOutput(response);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@NotBlank String id) {
        log.info("Delete document in index /products/_doc/{}", id);

        this.productService.deleteProductById(id);
    }

    @Get("/{?query*}")
    @Status(HttpStatus.OK)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductOutput> search(@Valid ProductQuery query) {
        log.info("Search document in index /products by query: {}", query);

        var domain = this.mapper.toDomain(query);

        var response = this.productService.searchProductByQuery(domain);

        return response.stream().map(r -> this.mapper.toOutput(r)).collect(Collectors.toList());
    }
}
