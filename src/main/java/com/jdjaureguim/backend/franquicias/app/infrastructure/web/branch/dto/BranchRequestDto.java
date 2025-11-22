package com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto;

import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductRequestDto;

import java.util.List;

public class BranchRequestDto {

    private String name;
    private List<ProductRequestDto> products;

    public BranchRequestDto() {
    }

    public BranchRequestDto(String name, List<ProductRequestDto> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductRequestDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequestDto> products) {
        this.products = products;
    }
}
