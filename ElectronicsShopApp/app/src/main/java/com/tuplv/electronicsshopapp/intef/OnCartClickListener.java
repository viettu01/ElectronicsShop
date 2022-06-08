package com.tuplv.electronicsshopapp.intef;


import com.tuplv.electronicsshopapp.model.Cart;
import com.tuplv.electronicsshopapp.model.Product;

public interface OnCartClickListener {
    void onClickDeleteProductInCart(long id);
    void onChangeQuantityProductInCart(Product product);
}
