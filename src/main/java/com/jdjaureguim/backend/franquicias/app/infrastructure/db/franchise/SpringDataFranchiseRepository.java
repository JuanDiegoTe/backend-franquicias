package com.jdjaureguim.backend.franquicias.app.infrastructure.db.franchise;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataFranchiseRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}
