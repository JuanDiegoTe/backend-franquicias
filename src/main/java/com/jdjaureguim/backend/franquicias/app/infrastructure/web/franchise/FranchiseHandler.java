package com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise;

import com.jdjaureguim.backend.franquicias.app.domain.models.Franchise;
import com.jdjaureguim.backend.franquicias.app.domain.models.TopProductByBranch;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.commons.dto.UpdateNameRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise.dto.CreateFranchiseRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise.dto.FranchiseResponseDto;
import com.jdjaureguim.backend.franquicias.app.usecase.franchise.CreateFranchiseUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.franchise.GetAllFranchisesUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.franchise.GetFranchiseByIdUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.franchise.UpdateFranchiseNameUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.product.GetTopStockProductsByBranchUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FranchiseHandler {

    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final UpdateFranchiseNameUseCase updateFranchiseNameUseCase;
    private final GetTopStockProductsByBranchUseCase topStockUseCase;
    private final GetAllFranchisesUseCase getAllFranchisesUseCase;
    private final GetFranchiseByIdUseCase getFranchiseByIdUseCase;

    public FranchiseHandler(CreateFranchiseUseCase createFranchiseUseCase,
                            UpdateFranchiseNameUseCase updateFranchiseNameUseCase,
                            GetTopStockProductsByBranchUseCase topStockUseCase, GetAllFranchisesUseCase getAllFranchisesUseCase, GetFranchiseByIdUseCase getFranchiseByIdUseCase) {
        this.createFranchiseUseCase = createFranchiseUseCase;
        this.updateFranchiseNameUseCase = updateFranchiseNameUseCase;
        this.topStockUseCase = topStockUseCase;
        this.getAllFranchisesUseCase = getAllFranchisesUseCase;
        this.getFranchiseByIdUseCase = getFranchiseByIdUseCase;
    }

    private FranchiseResponseDto toDto(Franchise franchise) {
        return new FranchiseResponseDto(
                franchise.getId(),
                franchise.getName()
        );
    }

    public Mono<ServerResponse> createFranchise(ServerRequest request) {
        return request.bodyToMono(CreateFranchiseRequestDto.class)
                .flatMap(dto -> createFranchiseUseCase.execute(dto.getName()))
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }

    public Mono<ServerResponse> updateFranchiseName(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");
        return request.bodyToMono(UpdateNameRequestDto.class)
                .flatMap(dto -> updateFranchiseNameUseCase.execute(franchiseId, dto.getName()))
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }

    public Mono<ServerResponse> getTopProductsByBranch(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");
        Flux<TopProductByBranch> body = topStockUseCase.execute(franchiseId);
        return ServerResponse.ok().body(body, TopProductByBranch.class);
    }

    public Mono<ServerResponse> getAllFranchise(ServerRequest request) {
        Flux<Franchise> body = getAllFranchisesUseCase.execute();
        return ServerResponse.ok().body(body, Franchise.class);
    }

    public Mono<ServerResponse> getByIdFranchise(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");
        Mono<Franchise> body = getFranchiseByIdUseCase.execute(franchiseId);
        return ServerResponse.ok().body(body, Franchise.class);
    }

}
