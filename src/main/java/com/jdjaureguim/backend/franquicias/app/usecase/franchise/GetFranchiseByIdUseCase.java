package com.jdjaureguim.backend.franquicias.app.usecase.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import reactor.core.publisher.Mono;

public class GetFranchiseByIdUseCase {

    private final FranchiseRepository franchiseRepository;

    public GetFranchiseByIdUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Mono<Franchise> execute(String franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new RuntimeException("No existe el franchise con el id: " + franchiseId)));
    }

}
