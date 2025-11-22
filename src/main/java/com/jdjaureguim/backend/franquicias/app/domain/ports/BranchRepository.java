package com.jdjaureguim.backend.franquicias.app.domain.ports;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchRepository {
    Mono<Branch> save(Branch branch);
    Mono<Branch> findById(String id);
    Flux<Branch> findAll();
    Flux<Branch> findByFranchiseId(String franchiseId);

}
