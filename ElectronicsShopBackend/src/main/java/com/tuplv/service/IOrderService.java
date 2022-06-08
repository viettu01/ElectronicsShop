package com.tuplv.service;

import com.tuplv.dto.OrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll(Pageable pageable);
    int getTotalItem();
    OrderDTO findById(Long id);
    List<OrderDTO> findByUserId(Long userId);
    OrderDTO save(OrderDTO dto);
    boolean insertOrderAndOrderDetailUseProc(long userId, String fullName, String address, String phone, String note, double price_total);
}
