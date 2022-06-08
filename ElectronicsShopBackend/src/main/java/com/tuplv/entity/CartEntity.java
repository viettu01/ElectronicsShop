package com.tuplv.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {
    @OneToMany(mappedBy = "cart")
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "cart")
    private List<CartProductEntity> cartProducts = new ArrayList<>();

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
