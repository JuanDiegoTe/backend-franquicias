package com.jdjaureguim.backend.franquicias.app.usecase.branch;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import reactor.core.publisher.Flux;

public class GetAllBranchesUseCase {

    private final BranchRepository branchRepository;

    public GetAllBranchesUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Flux<Branch> execute() {
        return branchRepository.findAll();
    }

}
