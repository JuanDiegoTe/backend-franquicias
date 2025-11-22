package com.jdjaureguim.backend.franquicias.app.usecase.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Mono;

public class AddProductToBranchUseCase {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public AddProductToBranchUseCase(BranchRepository branchRepository,
                                     ProductRepository productRepository) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    public Mono<Product> execute(String branchId, String productName, int stock) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new RuntimeException("Branch not found")))
                .flatMap(branch -> {
                    Product product = new Product();
                    product.setBranchId(branch.getId());
                    product.setName(productName);
                    product.setStock(stock);
                    return productRepository.save(product);
                });
    }

}
