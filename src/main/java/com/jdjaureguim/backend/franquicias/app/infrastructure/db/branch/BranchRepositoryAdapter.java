package com.jdjaureguim.backend.franquicias.app.infrastructure.db.branch;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.infrastructure.db.product.ProductDocument;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BranchRepositoryAdapter implements BranchRepository {

    private final SpringDataBranchRepository repository;

    public BranchRepositoryAdapter(SpringDataBranchRepository repository) {
        this.repository = repository;
    }

    private Branch toDomain(BranchDocument doc) {
        if (doc == null) return null;
        return new Branch(
                doc.getId(),
                doc.getFranchiseId(),
                doc.getName()
        );
    }

    private BranchDocument toDocument(Branch branch) {
        if (branch == null) return null;
        return new BranchDocument(
                branch.getId(),
                branch.getFranchiseId(),
                branch.getName()
        );
    }

    @Override
    public Mono<Branch> save(Branch branch) {
        return repository.save(toDocument(branch))
                .map(this::toDomain);
    }

    @Override
    public Mono<Branch> findById(String id) {
        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Branch> findByFranchiseId(String franchiseId) {
        return repository.findByFranchiseId(franchiseId)
                .map(this::toDomain);
    }

    @Override
    public Flux<Branch> findAll() {
        return repository.findAll()
                .map(this::toDomain);
    }

}
