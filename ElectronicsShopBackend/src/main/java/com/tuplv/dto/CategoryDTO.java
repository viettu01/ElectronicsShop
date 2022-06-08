package com.tuplv.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO extends AbstractDTO<CategoryDTO> {
    private String name;
    private String code;
    private String avatar;

    List<ProductDTO> listProduct = new ArrayList<>();

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

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductDTO> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
