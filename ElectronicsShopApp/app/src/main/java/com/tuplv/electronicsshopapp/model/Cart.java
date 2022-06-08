package com.tuplv.electronicsshopapp.model;

import java.util.ArrayList;
import java.util.List;

public class Cart extends BaseModel {
    private long userId;
    private List<Product> products = new ArrayList<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
