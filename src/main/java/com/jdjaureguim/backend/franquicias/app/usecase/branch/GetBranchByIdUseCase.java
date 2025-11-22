package com.jdjaureguim.backend.franquicias.app.usecase.branch;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import reactor.core.publisher.Mono;

public class GetBranchByIdUseCase {

    private final BranchRepository branchRepository;

    public GetBranchByIdUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Mono<Branch> execute(String branchId) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new RuntimeException("No existe la sucursal con el id: " + branchId)));
    }

}
