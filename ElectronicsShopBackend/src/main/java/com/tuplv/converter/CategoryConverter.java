package com.tuplv.converter;

import com.tuplv.dto.CategoryDTO;
import com.tuplv.dto.ProductDTO;
import com.tuplv.entity.CategoryEntity;
import com.tuplv.entity.ProductEntity;
import com.tuplv.util.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    @Autowired
    VNCharacterUtils vnCharacterUtils;

    @Autowired
    ProductConverter productConverter;

    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO result = new CategoryDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());

        result.setName(entity.getName());
        result.setCode(entity.getCode());
        result.setAvatar(entity.getAvatar());

        List<ProductDTO> listProductDTO = new ArrayList<>();
        for (ProductEntity productEntity : entity.getListProduct()) {
            listProductDTO.add(productConverter.toDTO(productEntity));
        }
        result.setListProduct(listProductDTO);

        return result;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity result = new CategoryEntity();
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        result.setAvatar(dto.getAvatar());
        return result;
    }

    public CategoryEntity toEntity(CategoryEntity categoryEntity, CategoryDTO categoryDTO) {
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCode(categoryDTO.getCode());
        categoryEntity.setAvatar(categoryDTO.getAvatar());
        return categoryEntity;
    }

    public String toCode(String name) {
        String newName = VNCharacterUtils.removeAccent(name).toLowerCase();
        String code = "";
        for (int i = 0; i < newName.split(" ").length; i++) {
            code += newName.split(" ")[i] + "-";
        }
        return code.trim().substring(0, code.length() - 1);
    }
}
