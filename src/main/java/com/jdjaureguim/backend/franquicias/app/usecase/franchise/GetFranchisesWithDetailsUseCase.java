package com.jdjaureguim.backend.franquicias.app.usecase.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.models.BranchDetails;
import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.models.FranchiseDetails;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetFranchisesWithDetailsUseCase {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public GetFranchisesWithDetailsUseCase(FranchiseRepository franchiseRepository,
                                           BranchRepository branchRepository,
                                           ProductRepository productRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    public Flux<FranchiseDetails> execute() {
        return franchiseRepository.findAll()
                .flatMap(this::buildFranchiseDetails);
    }

    private Mono<FranchiseDetails> buildFranchiseDetails(Franchise franchise) {
        return branchRepository.findByFranchiseId(franchise.getId())
                .flatMap(this::buildBranchDetails)
                .collectList()
                .map(branchDetailsList ->
                        new FranchiseDetails(
                                franchise.getId(),
                                franchise.getName(),
                                branchDetailsList
                        )
                );
    }

    private Mono<BranchDetails> buildBranchDetails(Branch branch) {
        return productRepository.findByBranchId(branch.getId())
                .collectList()
                .map(products -> new BranchDetails(
                        branch.getId(),
                        branch.getName(),
                        products
                ));
    }

}
