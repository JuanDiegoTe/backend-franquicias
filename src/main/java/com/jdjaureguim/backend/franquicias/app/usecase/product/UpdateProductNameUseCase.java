package com.jdjaureguim.backend.franquicias.app.usecase.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Mono;

public class UpdateProductNameUseCase {
    private final ProductRepository productRepository;

    public UpdateProductNameUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> execute(String productId, String newName) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .flatMap(product -> {
                    product.setName(newName);
                    return productRepository.save(product);
                });
    }
}
