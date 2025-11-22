package com.jdjaureguim.backend.franquicias.app.domain.ports;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {

    Mono<Franchise> save(Franchise franchise);
    Mono<Franchise> findById(String id);
    Flux<Franchise> findAll();

}
