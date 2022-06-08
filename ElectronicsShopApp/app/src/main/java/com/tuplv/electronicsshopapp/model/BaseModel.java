package com.tuplv.electronicsshopapp.model;

import java.io.Serializable;

public class BaseModel implements Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
