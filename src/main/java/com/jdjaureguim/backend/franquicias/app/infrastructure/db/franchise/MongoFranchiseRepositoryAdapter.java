package com.jdjaureguim.backend.franquicias.app.infrastructure.db.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MongoFranchiseRepositoryAdapter implements FranchiseRepository {

    private final SpringDataFranchiseRepository repository;

    public MongoFranchiseRepositoryAdapter(SpringDataFranchiseRepository repository) {
        this.repository = repository;
    }

    private Franchise toDomain(FranchiseDocument doc) {
        if (doc == null) return null;
        return new Franchise(doc.getId(), doc.getName());
    }

    private FranchiseDocument toDocument(Franchise franchise) {
        if (franchise == null) return null;
        return new FranchiseDocument(franchise.getId(), franchise.getName());
    }

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return repository.save(toDocument(franchise))
                .map(this::toDomain);
    }

    @Override
    public Mono<Franchise> findById(String id) {
        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Franchise> findAll() {
        return repository.findAll()
                .map(this::toDomain);
    }



}
