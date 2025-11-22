package com.jdjaureguim.backend.franquicias.app.infrastructure.web.product;

import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.commons.dto.UpdateNameRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.CreateProductRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductResponseDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.UpdateStockRequestDto;
import com.jdjaureguim.backend.franquicias.app.usecase.product.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    private final AddProductToBranchUseCase addProductToBranchUseCase;
    private final RemoveProductFromBranchUseCase removeProductFromBranchUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final UpdateProductNameUseCase updateProductNameUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;

    public ProductHandler(AddProductToBranchUseCase addProductToBranchUseCase,
                          RemoveProductFromBranchUseCase removeProductFromBranchUseCase,
                          UpdateProductStockUseCase updateProductStockUseCase,
                          UpdateProductNameUseCase updateProductNameUseCase,
                          GetAllProductsUseCase getAllProductsUseCase,
                          GetProductByIdUseCase getProductByIdUseCase) {
        this.addProductToBranchUseCase = addProductToBranchUseCase;
        this.removeProductFromBranchUseCase = removeProductFromBranchUseCase;
        this.updateProductStockUseCase = updateProductStockUseCase;
        this.updateProductNameUseCase = updateProductNameUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getBranchId(),
                product.getName(),
                product.getStock()
        );
    }

    public Mono<ServerResponse> addProductToBranch(ServerRequest request) {
        String branchId = request.pathVariable("branchId");
        return request.bodyToMono(CreateProductRequestDto.class)
                .flatMap(dto -> addProductToBranchUseCase.execute(branchId, dto.getName(), dto.getStock()))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> removeProduct(ServerRequest request) {
        String productId = request.pathVariable("productId");
        return removeProductFromBranchUseCase.execute(productId)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateProductStock(ServerRequest request) {
        String productId = request.pathVariable("productId");
        return request.bodyToMono(UpdateStockRequestDto.class)
                .flatMap(dto -> updateProductStockUseCase.execute(productId, dto.getStock()))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> updateProductName(ServerRequest request) {
        String productId = request.pathVariable("productId");
        return request.bodyToMono(UpdateNameRequestDto.class)
                .flatMap(dto -> updateProductNameUseCase.execute(productId, dto.getName()))
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        Flux<ProductResponseDto> body = getAllProductsUseCase.execute()
                .map(this::toDto);

        return ServerResponse.ok().body(body, ProductResponseDto.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        String productId = request.pathVariable("productId");

        return getProductByIdUseCase.execute(productId)
                .map(this::toDto)
                .flatMap(dto -> ServerResponse.ok().bodyValue(dto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
