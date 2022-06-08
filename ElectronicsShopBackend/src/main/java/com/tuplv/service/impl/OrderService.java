package com.tuplv.service.impl;

import com.tuplv.converter.OrderConverter;
import com.tuplv.dto.OrderDTO;
import com.tuplv.entity.OrderEntity;
import com.tuplv.repository.OrderRepository;
import com.tuplv.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderConverter orderConverter;

    @Override
    public List<OrderDTO> findAll(Pageable pageable) {
        List<OrderDTO> models = new ArrayList<>();
        List<OrderEntity> entities = orderRepository.findAll(pageable).getContent();
        for (OrderEntity item : entities) {
            OrderDTO dto = orderConverter.toDTO(item);
            models.add(dto);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) orderRepository.count();
    }

    @Override
    public OrderDTO findById(Long id) {
        OrderEntity entity = orderRepository.findOne(id);
        return orderConverter.toDTO(entity);
    }

    @Override
    public List<OrderDTO> findByUserId(Long userId) {
        List<OrderDTO> orders = new ArrayList<>();
        List<OrderEntity> entities = orderRepository.findByUserId(userId);
        for (OrderEntity entity : entities) {
            orders.add(orderConverter.toDTO(entity));
        }
        return orders;
    }

    @Override
    public OrderDTO save(OrderDTO newOrder) {
        OrderEntity orderEntity = new OrderEntity();
        if (newOrder.getId() != null) {
            OrderEntity oldOrder = orderRepository.findOne(newOrder.getId());
            orderEntity = orderConverter.toEntity(oldOrder, newOrder);
        } else {
            orderEntity = orderConverter.toEntity(newOrder);
        }
        return orderConverter.toDTO(orderRepository.save(orderEntity));
    }

    @Override
    @Transactional
    public boolean insertOrderAndOrderDetailUseProc(long userId, String fullName, String address, String phone, String note, double price_total) {
        String sql = "Execute insertTblOrderAndTblOrderDetail @userId = ?, @fullname = ?, @address = ?, @phone = ?, @note = ?, @price_total = ?";

        return jdbcTemplate.update(sql, userId, fullName, address, phone, note, price_total) > 0;
    }
}
