package com.jdjaureguim.backend.franquicias.app.usecase.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import reactor.core.publisher.Mono;

public class UpdateFranchiseNameUseCase {

    private final FranchiseRepository franchiseRepository;

    public UpdateFranchiseNameUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Mono<Franchise> execute(String franchiseId, String newName) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new RuntimeException("Franchise not found")))
                .flatMap(franchise -> {
                    franchise.setName(newName);
                    return franchiseRepository.save(franchise);
                });
    }

}
