package com.jdjaureguim.backend.franquicias.app.usecase.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Mono;

public class UpdateProductStockUseCase {

    private final ProductRepository productRepository;

    public UpdateProductStockUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> execute(String productId, int newStock) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .flatMap(product -> {
                    product.setStock(newStock);
                    return productRepository.save(product);
                });
    }

}
