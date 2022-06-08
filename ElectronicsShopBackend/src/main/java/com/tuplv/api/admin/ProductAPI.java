package com.tuplv.api.admin;

import com.tuplv.dto.ProductDTO;
import com.tuplv.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "productAPIOfAdmin")
@RequestMapping(value = "/api")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @PostMapping(value = "/product")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping(value = "/product")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping(value = "/product")
    public void deleteProduct(@RequestBody long[] ids) {
        productService.delete(ids);
    }
}
