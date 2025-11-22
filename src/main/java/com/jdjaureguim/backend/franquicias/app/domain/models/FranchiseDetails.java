package com.jdjaureguim.backend.franquicias.app.domain.models;

import java.util.List;

public class FranchiseDetails {

    private String id;
    private String name;
    private List<BranchDetails> branches;

    public FranchiseDetails() {
    }

    public FranchiseDetails(String id, String name, List<BranchDetails> branches) {
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

    public List<BranchDetails> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDetails> branches) {
        this.branches = branches;
    }
}
