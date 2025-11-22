package com.jdjaureguim.backend.franquicias.app.usecase.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import reactor.core.publisher.Flux;

public class GetAllFranchisesUseCase {

    private final FranchiseRepository franchiseRepository;

    public GetAllFranchisesUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Flux<Franchise> execute() {
        return franchiseRepository.findAll();
    }

}
