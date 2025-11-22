package com.jdjaureguim.backend.franquicias.app.usecase.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.models.TopProductByBranch;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Flux;

import java.util.Comparator;

public class GetTopStockProductsByBranchUseCase {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public GetTopStockProductsByBranchUseCase(BranchRepository branchRepository,
                                                          ProductRepository productRepository) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    public Flux<TopProductByBranch> execute(String franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId)
                .flatMap(branch ->
                        productRepository.findByBranchId(branch.getId())
                                .sort(Comparator.comparingInt(Product::getStock).reversed())
                                .next() // toma el primero (mayor stock)
                                .map(p -> new TopProductByBranch(
                                        franchiseId,
                                        branch.getId(),
                                        branch.getName(),
                                        p.getId(),
                                        p.getName(),
                                        p.getStock()
                                ))
                                .flux()
                );
    }

}
