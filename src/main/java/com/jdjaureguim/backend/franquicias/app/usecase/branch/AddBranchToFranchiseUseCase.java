package com.jdjaureguim.backend.franquicias.app.usecase.branch;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import reactor.core.publisher.Mono;

public class AddBranchToFranchiseUseCase {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;

    public AddBranchToFranchiseUseCase(FranchiseRepository franchiseRepository,
                                       BranchRepository branchRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
    }

    public Mono<Branch> execute(String franchiseId, String branchName) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new RuntimeException("Franchise not found")))
                .flatMap(franchise -> {
                    Branch branch = new Branch();
                    branch.setFranchiseId(franchise.getId());
                    branch.setName(branchName);
                    return branchRepository.save(branch);
                });
    }

}
