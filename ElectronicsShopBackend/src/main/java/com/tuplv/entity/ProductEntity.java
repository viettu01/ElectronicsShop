package com.tuplv.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column
    private String avatar;

    @Column
    private Double price;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<CartProductEntity> cartProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
