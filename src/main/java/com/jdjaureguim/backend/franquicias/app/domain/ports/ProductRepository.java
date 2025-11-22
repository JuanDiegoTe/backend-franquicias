package com.jdjaureguim.backend.franquicias.app.domain.ports;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> save(Product product);
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Flux<Product> findByBranchId(String branchId);
    Mono<Void> deleteById(String id);

}
