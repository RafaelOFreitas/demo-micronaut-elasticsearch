package br.com.freitas.adapter.web.mapper;

import br.com.freitas.adapter.web.dto.ProductInput;
import br.com.freitas.adapter.web.dto.ProductOutput;
import br.com.freitas.adapter.web.dto.ProductUpdatable;
import br.com.freitas.core.domain.Product;
import javax.annotation.processing.Generated;
import javax.inject.Named;
import javax.inject.Singleton;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-14T20:36:09-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Singleton
@Named
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toDomain(ProductInput product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setName( product.getName() );
        product1.setAmount( product.getAmount() );
        product1.setPrice( product.getPrice() );
        product1.setDescription( product.getDescription() );
        product1.setExpirationDate( product.getExpirationDate() );

        return product1;
    }

    @Override
    public Product toDomain(ProductUpdatable product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setName( product.getName() );
        product1.setAmount( product.getAmount() );
        product1.setPrice( product.getPrice() );
        product1.setDescription( product.getDescription() );
        product1.setExpirationDate( product.getExpirationDate() );

        return product1;
    }

    @Override
    public ProductOutput toOutput(Product response) {
        if ( response == null ) {
            return null;
        }

        ProductOutput productOutput = new ProductOutput();

        productOutput.setName( response.getName() );
        productOutput.setAmount( response.getAmount() );
        productOutput.setPrice( response.getPrice() );
        productOutput.setDescription( response.getDescription() );
        productOutput.setExpirationDate( response.getExpirationDate() );

        return productOutput;
    }
}
