package com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch;

import com.jdjaureguim.backend.franquicias.app.domain.models.Branch;
import com.jdjaureguim.backend.franquicias.app.domain.models.Product;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto.BranchRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto.BranchResponseDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto.CreateBranchRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.commons.dto.UpdateNameRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductRequestDto;
import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductResponseDto;
import com.jdjaureguim.backend.franquicias.app.usecase.branch.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchHandler {

    private final AddBranchToFranchiseUseCase addBranchToFranchiseUseCase;
    private final UpdateBranchNameUseCase updateBranchNameUseCase;
    private final GetAllBranchesUseCase getAllBranchesUseCase;
    private final GetBranchByIdUseCase getBranchByIdUseCase;

    public BranchHandler(AddBranchToFranchiseUseCase addBranchToFranchiseUseCase,
                         UpdateBranchNameUseCase updateBranchNameUseCase,
                         GetAllBranchesUseCase getAllBranchesUseCase,
                         GetBranchByIdUseCase getBranchByIdUseCase) {
        this.addBranchToFranchiseUseCase = addBranchToFranchiseUseCase;
        this.updateBranchNameUseCase = updateBranchNameUseCase;
        this.getAllBranchesUseCase = getAllBranchesUseCase;
        this.getBranchByIdUseCase = getBranchByIdUseCase;
    }

    private BranchResponseDto toDto(Branch branch) {
        return new BranchResponseDto(
                branch.getId(),
                branch.getName()
        );
    }

    public Mono<ServerResponse> addBranchToFranchise(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");
        return request.bodyToMono(CreateBranchRequestDto.class)
                .flatMap(dto -> addBranchToFranchiseUseCase.execute(franchiseId, dto.getName()))
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

    public Mono<ServerResponse> updateBranchName(ServerRequest request) {
        String branchId = request.pathVariable("branchId");
        return request.bodyToMono(UpdateNameRequestDto.class)
                .flatMap(dto -> updateBranchNameUseCase.execute(branchId, dto.getName()))
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

    public Mono<ServerResponse> getAllBranches(ServerRequest request) {
        Flux<BranchResponseDto> body = getAllBranchesUseCase.execute()
                .map(this::toDto);

        return ServerResponse.ok().body(body, BranchResponseDto.class);
    }

    public Mono<ServerResponse> getBranchById(ServerRequest request) {
        String branchId = request.pathVariable("branchId");

        return getBranchByIdUseCase.execute(branchId)
                .map(this::toDto)
                .flatMap(dto -> ServerResponse.ok().bodyValue(dto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
