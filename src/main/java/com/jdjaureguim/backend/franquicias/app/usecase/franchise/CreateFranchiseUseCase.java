package com.jdjaureguim.backend.franquicias.app.usecase.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import reactor.core.publisher.Mono;

public class CreateFranchiseUseCase {

    private final FranchiseRepository franchiseRepository;

    public CreateFranchiseUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Mono<Franchise> execute(String name) {
        Franchise franchise = new Franchise();
        franchise.setName(name);
        return franchiseRepository.save(franchise);
    }

}
