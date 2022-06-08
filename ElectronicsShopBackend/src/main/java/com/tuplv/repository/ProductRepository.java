package com.tuplv.repository;

import com.tuplv.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findOneByCategoryId(long categoryId);

    List<ProductEntity> findByNameContaining(String name);

    void deleteByCategoryId(long categoryId);

    boolean existsByName(String name);
}
