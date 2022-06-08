package com.tuplv.api.web;

import com.tuplv.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cart")
public class CartAPI {

    @Autowired
    ICartService cartService;

    @RequestMapping(value = "/findById", produces = "text/plain;charset=UTF-8")
    public String getCartById(@RequestParam("id") long id) {

        return null;
    }
}
