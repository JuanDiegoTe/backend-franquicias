package com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto;

import com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto.ProductResponseDto;

import java.util.List;

public class BranchResponseDto {

    private String id;
    private String name;
    private List<ProductResponseDto> products;

    public BranchResponseDto() {}

    public BranchResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public BranchResponseDto(String id, String name, List<ProductResponseDto> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<ProductResponseDto> getProducts() { return products; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setProducts(List<ProductResponseDto> products) { this.products = products; }

}
