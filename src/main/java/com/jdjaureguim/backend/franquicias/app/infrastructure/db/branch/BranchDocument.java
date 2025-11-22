package com.jdjaureguim.backend.franquicias.app.infrastructure.db.branch;

import com.jdjaureguim.backend.franquicias.app.infrastructure.db.product.ProductDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "branches")
public class BranchDocument {

    @Id
    private String id;
    private String franchiseId;
    private String name;

    public BranchDocument() {
    }

    public BranchDocument(String id, String franchiseId, String name) {
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