package com.tuplv.service.impl;

import com.tuplv.converter.CategoryConverter;
import com.tuplv.dto.CategoryDTO;
import com.tuplv.entity.CategoryEntity;
import com.tuplv.repository.CategoryRepository;
import com.tuplv.repository.ProductRepository;
import com.tuplv.service.ICategoryService;
import com.tuplv.uploadFile.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    ServletContext context;

    @Override
    public List<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> models = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll(pageable).getContent();
        for (CategoryEntity item : entities) {
            CategoryDTO dto = categoryConverter.toDTO(item);
            models.add(dto);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            result.put(item.getCode(), item.getName());
        }
        return result;
    }

    @Override
    public CategoryDTO findById(Long id) {
        CategoryEntity entity = categoryRepository.findOne(id);
        return categoryConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO newCategory) {
        CategoryEntity categoryEntity = new CategoryEntity();
        if (newCategory.getId() != null) {
            CategoryEntity oldCategory = categoryRepository.findOne(newCategory.getId());
            if (newCategory.getAvatar() == null) {
                newCategory.setAvatar(oldCategory.getAvatar());
            } else {
                File file = new File(context.getRealPath("/template/images/category") + File.separator + oldCategory.getAvatar());
                file.delete();
            }
            categoryEntity = categoryConverter.toEntity(oldCategory, newCategory);
        } else {
            categoryEntity = categoryConverter.toEntity(newCategory);
        }
        return categoryConverter.toDTO(categoryRepository.save(categoryEntity));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            if (productRepository.findOneByCategoryId(id) != null) {
                File avatarProduct = new File(context.getRealPath("/template/images/product") + File.separator + productRepository.findOneByCategoryId(id).getAvatar());
                avatarProduct.delete();
                productRepository.deleteByCategoryId(id);
            }
            File avatarCategory = new File(context.getRealPath("/template/images/category") + File.separator + categoryRepository.findOne(id).getAvatar());
            avatarCategory.delete();
            categoryRepository.delete(id);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

}
