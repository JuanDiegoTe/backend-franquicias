package com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto;

public class ProductResponseDto {

    private String id;
    private String branchId;
    private String name;
    private Integer stock;

    public ProductResponseDto() {}

    public ProductResponseDto(String id, String branchId, String name, int stock) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
