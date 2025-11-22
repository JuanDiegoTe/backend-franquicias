package com.jdjaureguim.backend.franquicias.app.infrastructure.db.branch;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SpringDataBranchRepository extends ReactiveMongoRepository<BranchDocument, String> {
    Flux<BranchDocument> findByFranchiseId(String franchiseId);
}
