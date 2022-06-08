package com.tuplv.api.web;

import com.google.gson.Gson;
import com.tuplv.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "categoryAPIOfWeb")
@RequestMapping(value = "/api/category")
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/get-all", produces = "text/plain;charset=UTF-8")
    public String findAll() {

        return new Gson().toJson(categoryService.findAll(null));
    }

    @GetMapping(value = "/get-by-id", produces = "text/plain;charset=UTF-8")
    public String findById(@RequestParam("id") Long id) {

        return new Gson().toJson(categoryService.findById(id));
    }
}
