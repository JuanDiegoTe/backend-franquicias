package com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise.dto;

public class FranchiseResponseDto {

    private String id;
    private String name;

    public FranchiseResponseDto() {
    }

    public FranchiseResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
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
}
