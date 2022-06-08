package com.tuplv.service;

public interface ICartProductService {
    boolean insertUseProc(long cartId, long productId, long quantity);
    boolean updateQuantityProduct(long cartId, long productId, long quantity);
    boolean deleteProduct(long cartId, long productId);
}
