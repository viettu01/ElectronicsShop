package com.tuplv.converter;

import com.tuplv.dto.OrderDTO;
import com.tuplv.dto.OrderDetailDTO;
import com.tuplv.entity.OrderDetailEntity;
import com.tuplv.entity.OrderEntity;
import com.tuplv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetailConverter orderDetailConverter;

    public OrderDTO toDTO(OrderEntity entity) {
        OrderDTO result = new OrderDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());

        result.setUserId(entity.getUser().getId());
        result.setFullName(entity.getFullName());
        result.setPhone(entity.getPhone());
        result.setAddress(entity.getAddress());
        result.setEmail(entity.getEmail());
        result.setNote(entity.getNote());
        result.setPriceTotal(entity.getPriceTotal());
        result.setStatus(entity.getStatus());

        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        for (OrderDetailEntity orderDetailEntity : entity.getOrderDetails()) {
            orderDetails.add(orderDetailConverter.toDTO(orderDetailEntity));
        }
        result.setOrderDetails(orderDetails);

        return result;
    }

    public OrderEntity toEntity(OrderDTO dto) {
        OrderEntity result = new OrderEntity();
        result.setUser(userRepository.findOne(dto.getUserId()));
        result.setFullName(dto.getFullName());
        result.setPhone(dto.getPhone());
        result.setAddress(dto.getAddress());
        result.setEmail(dto.getEmail());
        result.setNote(dto.getNote());
        result.setPriceTotal(dto.getPriceTotal());
        result.setStatus(dto.getStatus());
        return result;
    }

    public OrderEntity toEntity(OrderEntity orderEntity, OrderDTO orderDTO) {
        orderEntity.setUser(userRepository.findOne(orderDTO.getUserId()));
        orderEntity.setFullName(orderDTO.getFullName());
        orderEntity.setPhone(orderDTO.getPhone());
        orderEntity.setAddress(orderDTO.getAddress());
        orderEntity.setEmail(orderDTO.getEmail());
        orderEntity.setNote(orderDTO.getNote());
        orderEntity.setPriceTotal(orderDTO.getPriceTotal());
        orderEntity.setStatus(orderDTO.getStatus());

        return orderEntity;
    }
}
