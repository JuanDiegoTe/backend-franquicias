package com.jdjaureguim.backend.franquicias.app.usecase.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Flux;

public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> execute() {
        return productRepository.findAll();
    }

}
