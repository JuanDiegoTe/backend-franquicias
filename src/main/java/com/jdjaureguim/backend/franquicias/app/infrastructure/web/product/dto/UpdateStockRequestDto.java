package com.jdjaureguim.backend.franquicias.app.infrastructure.web.product.dto;

public class UpdateStockRequestDto {

    private Integer stock;

    public UpdateStockRequestDto() {
    }

    public UpdateStockRequestDto(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
