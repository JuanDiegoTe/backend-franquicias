package com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto;

public class CreateBranchRequestDto {

    private String name;

    public CreateBranchRequestDto() {
    }

    public CreateBranchRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
