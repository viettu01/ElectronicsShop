package com.tuplv.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column
    private String code;

    @Column
    private String avatar;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> listProduct = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ProductEntity> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductEntity> listProduct) {
        this.listProduct = listProduct;
    }
}
