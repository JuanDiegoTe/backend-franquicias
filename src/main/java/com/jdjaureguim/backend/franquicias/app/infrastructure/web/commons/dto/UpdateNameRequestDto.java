package com.jdjaureguim.backend.franquicias.app.infrastructure.web.commons.dto;

public class UpdateNameRequestDto {

    private String name;

    public UpdateNameRequestDto() {
    }

    public UpdateNameRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
