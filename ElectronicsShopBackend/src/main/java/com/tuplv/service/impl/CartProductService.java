package com.tuplv.service.impl;

import com.tuplv.repository.CartProductRepository;
import com.tuplv.service.ICartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartProductService implements ICartProductService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CartProductRepository cartProductRepository;

    @Override
    @Transactional
    public boolean insertUseProc(long cartId, long productId, long quantity) {
        String sql = "Execute insertDuplicateCartIdAndProductIdInCartProduct @cartId = ?, @productId = ?, @quantity = ?";

        return jdbcTemplate.update(sql, cartId, productId, quantity) > 0;
    }

    @Override
    @Transactional
    public boolean updateQuantityProduct(long cartId, long productId, long quantity) {
        String sql = "UPDATE cart_product SET quantity = ? WHERE cart_id = ? AND product_id = ?";

        return jdbcTemplate.update(sql, quantity, cartId, productId) > 0;
    }

    @Override
    @Transactional
    public boolean deleteProduct(long cartId, long productId) {
        return cartProductRepository.deleteByCartIdAndProductId(cartId, productId) > 0;
    }
}
