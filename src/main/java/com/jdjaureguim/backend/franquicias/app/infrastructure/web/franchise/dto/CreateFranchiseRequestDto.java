package com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise.dto;

public class CreateFranchiseRequestDto {

    private String name;

    public CreateFranchiseRequestDto() {
    }

    public CreateFranchiseRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
