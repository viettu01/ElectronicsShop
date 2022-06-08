package com.tuplv.converter;

import com.tuplv.dto.CommentDTO;
import com.tuplv.dto.ProductDTO;
import com.tuplv.entity.CommentEntity;
import com.tuplv.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    @Autowired
    CommentConverter commentConverter;

    public ProductDTO toDTO(ProductEntity entity) {
        ProductDTO result = new ProductDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());
        result.setName(entity.getName());
        result.setAvatar(entity.getAvatar());
        result.setPrice(entity.getPrice());
        result.setDescription(entity.getDescription());
        result.setCategoryId(entity.getCategory().getId());
        result.setCategoryCode(entity.getCategory().getCode());

        List<CommentDTO> comments = new ArrayList<>();
        for (CommentEntity comment : entity.getComments()) {
            comments.add(commentConverter.toDTO(comment));
        }
        result.setComments(comments);
        return result;
    }

    //Insert
    public ProductEntity toEntity(ProductDTO dto) {
        ProductEntity result = new ProductEntity();
        result.setName(dto.getName());
        result.setAvatar(dto.getAvatar());
        result.setPrice(dto.getPrice());
        result.setDescription(dto.getDescription());
        return result;
    }

    //Update
    public ProductEntity toEntity(ProductEntity productEntity, ProductDTO productDTO) {
        productEntity.setName(productDTO.getName());
        productEntity.setAvatar(productDTO.getAvatar());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        return productEntity;
    }
}
