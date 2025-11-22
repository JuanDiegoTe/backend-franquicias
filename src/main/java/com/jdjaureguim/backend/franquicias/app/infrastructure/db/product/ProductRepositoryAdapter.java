package com.jdjaureguim.backend.franquicias.app.infrastructure.db.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import com.jdjaureguim.backend.franquicias.app.infrastructure.db.branch.BranchDocument;
import com.jdjaureguim.backend.franquicias.app.infrastructure.db.branch.SpringDataBranchRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;

    public ProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    private Product toDomain(ProductDocument doc) {
        if (doc == null) return null;
        return new Product(
                doc.getId(),
                doc.getBranchId(),
                doc.getName(),
                doc.getStock()
        );
    }

    private ProductDocument toDocument(Product product) {
        if (product == null) return null;
        return new ProductDocument(
                product.getId(),
                product.getBranchId(),
                product.getName(),
                product.getStock()
        );
    }

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(toDocument(product))
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> findById(String id) {
        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Product> findByBranchId(String branchId) {
        return repository.findByBranchId(branchId)
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Flux<Product> findAll() {
        return repository.findAll()
                .map(this::toDomain);
    }

}
