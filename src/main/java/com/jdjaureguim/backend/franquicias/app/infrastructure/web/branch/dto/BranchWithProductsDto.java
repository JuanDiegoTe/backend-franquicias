package com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto;

import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductResponseDto;

import java.util.List;

public class BranchWithProductsDto {

    private String id;
    private String name;
    private List<ProductResponseDto> products;

    public BranchWithProductsDto() {
    }

    public BranchWithProductsDto(String id, String name, List<ProductResponseDto> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDto> products) {
        this.products = products;
    }
}
