package com.jdjaureguim.backend.franquicias.app.infrastructure.web.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductHandler handler) {
        return route(POST("/api/sucursales/{branchId}/productos"), handler::addProductToBranch)
                .andRoute(DELETE("/api/productos/{productId}"), handler::removeProduct)
                .andRoute(PUT("/api/productos/{productId}/stock"), handler::updateProductStock)
                .andRoute(PUT("/api/productos/{productId}/nombre"), handler::updateProductName)
                .andRoute(GET("/api/productos"), handler::getAllProducts)
                .andRoute(GET("/api/productos/{productId}"), handler::getProductById)
                ;
    }
}
