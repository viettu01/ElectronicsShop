package com.tuplv.api.admin;

import com.tuplv.dto.CategoryDTO;
import com.tuplv.service.ICategoryService;
import com.tuplv.service.IProductService;
import com.tuplv.uploadFile.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@RestController(value = "categoryAPIOfAdmin")
@RequestMapping(value = "/api")
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping(value = "/category")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryDTO;
    }

    @PutMapping(value = "/category")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {

        return categoryDTO;
    }

    @DeleteMapping(value = "/category")
    public void deleteCategory(@RequestBody long[] ids) {
        categoryService.delete(ids);
    }
}
