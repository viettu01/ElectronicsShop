package com.tuplv.repository;

import com.tuplv.entity.CartEntity;
import com.tuplv.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
