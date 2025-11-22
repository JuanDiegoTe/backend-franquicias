package com.jdjaureguim.backend.franquicias.app.infrastructure.db.franchise;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "franchises")
public class FranchiseDocument {

    @Id
    private String id;
    private String name;

    public FranchiseDocument() {
    }

    public FranchiseDocument(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
