package com.tuplv.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "fullname", columnDefinition = "NVARCHAR(255)")
    private String fullName;

    @Column
    private String phone;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column
    private String email;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String note;

    @Column(name = "price_total")
    private Double priceTotal;

    @Column
    private Integer status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetailEntities;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetailEntities) {
        this.orderDetailEntities = orderDetailEntities;
    }
}
