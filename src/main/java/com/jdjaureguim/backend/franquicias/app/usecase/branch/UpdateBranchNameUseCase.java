package com.jdjaureguim.backend.franquicias.app.usecase.branch;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import reactor.core.publisher.Mono;

public class UpdateBranchNameUseCase {
    private final BranchRepository branchRepository;

    public UpdateBranchNameUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Mono<Branch> execute(String branchId, String newName) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new RuntimeException("Branch not found")))
                .flatMap(branch -> {
                    branch.setName(newName);
                    return branchRepository.save(branch);
                });
    }
}
