package com.tuplv.converter;

import com.tuplv.dto.CommentDTO;
import com.tuplv.entity.CommentEntity;
import com.tuplv.repository.ProductRepository;
import com.tuplv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO result = new CommentDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());

        result.setUserId(entity.getUser().getId());
        result.setUserAvatar(entity.getUser().getAvatar());
        result.setFullNameUser(entity.getUser().getFullName());
        result.setProductId(entity.getProduct().getId());
        result.setContent(entity.getContent());
        return result;
    }

    //Insert
    public CommentEntity toEntity(CommentDTO dto) {
        CommentEntity result = new CommentEntity();
        result.setUser(userRepository.findOne(dto.getUserId()));
        result.setProduct(productRepository.findOne(dto.getProductId()));
        result.setContent(dto.getContent());
        return result;
    }

    public CommentEntity toEntity(CommentEntity commentEntity, CommentDTO commentDTO) {
        commentEntity.setUser(userRepository.findOne(commentDTO.getUserId()));
        commentEntity.setProduct(productRepository.findOne(commentDTO.getProductId()));
        commentEntity.setContent(commentDTO.getContent());
        return commentEntity;
    }
}
