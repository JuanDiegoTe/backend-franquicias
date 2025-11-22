package com.jdjaureguim.backend.franquicias.app.infrastructure.web.franchise.dto;

import com.jdjaureguim.backend.franquicias.app.infrastructure.web.branch.dto.BranchWithProductsDto;

import java.util.List;

public class FranchiseFullResponseDto {

    private String id;
    private String name;
    private List<BranchWithProductsDto> branches;

    public FranchiseFullResponseDto() {
    }

    public FranchiseFullResponseDto(String id, String name, List<BranchWithProductsDto> branches) {
        this.id = id;
        this.name = name;
        this.branches = branches;
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

    public List<BranchWithProductsDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchWithProductsDto> branches) {
        this.branches = branches;
    }
}
