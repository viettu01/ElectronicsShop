package com.tuplv.converter;

import com.tuplv.dto.CartProductDTO;
import com.tuplv.entity.CartProductEntity;
import com.tuplv.repository.CartRepository;
import com.tuplv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartProductConverter {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    public CartProductDTO toDTO(CartProductEntity entity) {
        CartProductDTO result = new CartProductDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());
        result.setCartId(entity.getCart().getId());
        result.setProductId(entity.getProduct().getId());
        result.setQuantity(entity.getQuantity());

        return result;
    }

    public CartProductEntity toEntity(CartProductDTO dto) {
        CartProductEntity result = new CartProductEntity();
        result.setProduct(productRepository.findOne(dto.getProductId()));
        result.setCart(cartRepository.findOne(dto.getCartId()));
        result.setQuantity(dto.getQuantity());
        return result;
    }
}
