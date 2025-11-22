package com.jdjaureguim.backend.franquicias.app.usecase.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.models.TopProductByBranch;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class RemoveProductFromBranchUseCase {

    private final ProductRepository productRepository;

    public RemoveProductFromBranchUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Void> execute(String productId) {
        return productRepository.deleteById(productId);
    }

}
