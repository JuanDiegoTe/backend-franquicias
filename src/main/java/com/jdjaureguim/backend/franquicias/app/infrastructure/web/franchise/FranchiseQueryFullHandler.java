package com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.BranchDetails;
import com.jdjaureguim.backend.franquicias.app.domain.models.FranchiseDetails;
import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto.BranchWithProductsDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise.dto.FranchiseFullResponseDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductResponseDto;
import com.jdjaureguim.backend.franquicias.app.usecase.franchise.GetFranchisesWithDetailsUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FranchiseQueryFullHandler {

    private final GetFranchisesWithDetailsUseCase getFranchisesWithDetailsUseCase;

    public FranchiseQueryFullHandler(GetFranchisesWithDetailsUseCase getFranchisesWithDetailsUseCase) {
        this.getFranchisesWithDetailsUseCase = getFranchisesWithDetailsUseCase;
    }

    private ProductResponseDto toProductDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getBranchId(),
                product.getName(),
                product.getStock()
        );
    }

    private BranchWithProductsDto toBranchWithProductsDto(BranchDetails branchDetails) {
        List<ProductResponseDto> products = branchDetails.getProducts() != null
                ? branchDetails.getProducts().stream()
                .map(this::toProductDto)
                .collect(Collectors.toList())
                : null;

        return new BranchWithProductsDto(
                branchDetails.getId(),
                branchDetails.getName(),
                products
        );
    }

    private FranchiseFullResponseDto toFranchiseFullDto(FranchiseDetails details) {
        List<BranchWithProductsDto> branches = details.getBranches() != null
                ? details.getBranches().stream()
                .map(this::toBranchWithProductsDto)
                .collect(Collectors.toList())
                : null;

        return new FranchiseFullResponseDto(
                details.getId(),
                details.getName(),
                branches
        );
    }

    public Mono<ServerResponse> getFranchisesWithDetails(ServerRequest request) {
        Flux<FranchiseFullResponseDto> body = getFranchisesWithDetailsUseCase.execute()
                .map(this::toFranchiseFullDto);

        return ServerResponse.ok().body(body, FranchiseFullResponseDto.class);
    }

}
