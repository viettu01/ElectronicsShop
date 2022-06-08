package com.tuplv.service;

import com.tuplv.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    List<CategoryDTO> findAll(Pageable pageable);
    int getTotalItem();
    Map<String, String> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO save(CategoryDTO dto);
    void delete(long[] ids);
    boolean existsByName(String name);
}
