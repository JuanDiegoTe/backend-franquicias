package com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FranchiseRouter {

    @Bean
    public RouterFunction<ServerResponse> franchiseFullRoutes(FranchiseQueryFullHandler handler) {
        return route(GET("/api/franquicias/full"), handler::getFranchisesWithDetails);
    }

    @Bean
    public RouterFunction<ServerResponse> franchiseRoutes(FranchiseHandler handler) {
        return route(POST("/api/franquicias"), handler::createFranchise)
                .andRoute(PUT("/api/franquicias/{franchiseId}/nombre"), handler::updateFranchiseName)
                .andRoute(GET("/api/franquicias/{franchiseId}/top-productos"), handler::getTopProductsByBranch)
                .andRoute(GET("/api/franquicias"), handler::getAllFranchise)
                .andRoute(GET("/api/franquicias/{franchiseId}"), handler::getByIdFranchise);
    }

}
