package com.tuplv.api.web;

import com.google.gson.Gson;
import com.tuplv.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "productAPIOfWeb")
@RequestMapping(value = "/api/product")
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/get-all", produces = "text/plain;charset=UTF-8")
    public String getAll() {

        return new Gson().toJson(productService.findAll(null));
    }

    @GetMapping(value = "/get-by-id", produces = "text/plain;charset=UTF-8")
    public String findById(@RequestParam("id") Long id) {

        return new Gson().toJson(productService.findById(id));
    }

    @GetMapping(value = "/get-by-id-cart", produces = "text/plain;charset=UTF-8")
    public String findByIdCart(@RequestParam("id") Long id) {

        return new Gson().toJson(productService.findByIdCart(id));
    }

    @GetMapping(value = "/get-by-name", produces = "text/plain;charset=UTF-8")
    public String findByName(@RequestParam("name") String name) {

        return new Gson().toJson(productService.findByName(name));
    }
}
