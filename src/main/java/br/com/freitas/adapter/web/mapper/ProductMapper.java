package br.com.freitas.adapter.web.mapper;

import br.com.freitas.adapter.web.dto.ProductInput;
import br.com.freitas.adapter.web.dto.ProductOutput;
import br.com.freitas.adapter.web.dto.ProductQuery;
import br.com.freitas.adapter.web.dto.ProductUpdatable;
import br.com.freitas.core.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface ProductMapper {

    Product toDomain(ProductInput product);

    Product toDomain(ProductQuery product);

    Product toDomain(ProductUpdatable product);

    ProductOutput toOutput(Product response);
}
