package com.tuplv.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column
    private String code;

    @ManyToMany(mappedBy = "listRole")
    private List<UserEntity> listUser = new ArrayList<>();

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

    public List<UserEntity> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserEntity> listUser) {
        this.listUser = listUser;
    }
}
