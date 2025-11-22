package com.jdjaureguim.backend.franquicias.app.domain.models;

import java.util.List;

public class BranchDetails {

    private String id;
    private String name;
    private List<Product> products;

    public BranchDetails() {
    }

    public BranchDetails(String id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
