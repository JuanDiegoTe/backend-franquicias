package com.jdjaureguim.backend.franquicias.app.domain.models;

import java.util.ArrayList;
import java.util.List;

public class Branch {

    private String id;
    private String franchiseId;
    private String name;

    public Branch() {
    }

    public Branch(String id, String franchiseId, String name) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(String franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
