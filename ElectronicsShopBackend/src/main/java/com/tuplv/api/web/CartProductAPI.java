package com.tuplv.api.web;

import com.google.gson.Gson;
import com.tuplv.service.ICartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cartProduct")
public class CartProductAPI {
    @Autowired
    ICartProductService cartProductService;

    @PostMapping(value = "/insert", produces = "text/plain;charset=UTF-8")
    public String insertUseProc(@RequestParam("cartId") long cartId, @RequestParam("productId") long productId,
                                @RequestParam("quantity") long quantity) {
        return new Gson().toJson(cartProductService.insertUseProc(cartId, productId, quantity));
    }

    @PostMapping(value = "/update", produces = "text/plain;charset=UTF-8")
    public String update(@RequestParam("cartId") long cartId, @RequestParam("productId") long productId,
                         @RequestParam("quantity") long quantity) {
        return new Gson().toJson(cartProductService.updateQuantityProduct(cartId, productId, quantity));
    }

    @PostMapping(value = "/delete", produces = "text/plain;charset=UTF-8")
    public String delete(@RequestParam("cartId") long cartId, @RequestParam("productId") long productId) {
        return new Gson().toJson(cartProductService.deleteProduct(cartId, productId));
    }
}
