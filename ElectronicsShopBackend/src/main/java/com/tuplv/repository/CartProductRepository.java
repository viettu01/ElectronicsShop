package com.tuplv.repository;

import com.tuplv.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    long deleteByCartIdAndProductId(long cartId, long productId);
}
