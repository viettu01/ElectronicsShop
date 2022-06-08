package com.tuplv.service;

import com.tuplv.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll(Pageable pageable);
    int getTotalItem();
    ProductDTO findById(Long id);
    ProductDTO save(ProductDTO dto);
    void delete(long[] ids);
    boolean existsByName(String name);
    List<ProductDTO> findByIdCart(long id);
    List<ProductDTO> findByName(String name);
}
