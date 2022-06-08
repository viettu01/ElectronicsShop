package com.tuplv.converter;

import com.tuplv.dto.OrderDTO;
import com.tuplv.dto.OrderDetailDTO;
import com.tuplv.entity.OrderDetailEntity;
import com.tuplv.entity.OrderEntity;
import com.tuplv.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter {

    @Autowired
    ProductConverter productConverter;

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderRepository orderRepository;

    public OrderDetailDTO toDTO(OrderDetailEntity entity) {
        OrderDetailDTO result = new OrderDetailDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());

        result.setQuantity(entity.getQuantity());
        result.setProduct(productConverter.toDTO(entity.getProduct()));
        result.setOrderId(entity.getOrder().getId());

        return result;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO dto) {
        OrderDetailEntity result = new OrderDetailEntity();
        result.setQuantity(dto.getQuantity());
        result.setProduct(productConverter.toEntity(dto.getProduct()));
        result.setOrder(orderRepository.findOne(dto.getOrderId()));
        return result;
    }
}
