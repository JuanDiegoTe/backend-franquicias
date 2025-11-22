package com.jdjaureguim.backend.franquicias.app.infrastructure.db.product;

import com.jdjaureguim.backend.franquicias.app.infrastructure.db.branch.BranchDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SpringDataProductRepository extends ReactiveMongoRepository<ProductDocument, String> {
    Flux<ProductDocument> findByBranchId(String branchId);
}