package com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto;

public class ProductRequestDto {

    private String name;
    private Integer stock;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
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
