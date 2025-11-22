package com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto;

public class CreateProductRequestDto {

    private String name;
    private int stock;

    public CreateProductRequestDto() {
    }

    public CreateProductRequestDto(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
