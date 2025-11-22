package com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BranchRouter {

    @Bean
    public RouterFunction<ServerResponse> branchRoutes(BranchHandler handler) {
        return route(POST("/api/franquicias/{franchiseId}/sucursales"), handler::addBranchToFranchise)
                .andRoute(PUT("/api/sucursales/{branchId}/nombre"), handler::updateBranchName)
                .andRoute(GET("/api/sucursales"), handler::getAllBranches)
                .andRoute(GET("/api/sucursales/{branchId}"), handler::getBranchById);
    }
}
