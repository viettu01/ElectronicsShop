package com.tuplv.service.impl;

import com.tuplv.converter.ProductConverter;
import com.tuplv.dto.ProductDTO;
import com.tuplv.entity.CategoryEntity;
import com.tuplv.entity.ProductEntity;
import com.tuplv.repository.CategoryRepository;
import com.tuplv.repository.ProductRepository;
import com.tuplv.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ServletContext context;

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> models = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findAll(pageable).getContent();
        for (ProductEntity item : entities) {
            ProductDTO dto = productConverter.toDTO(item);
            models.add(dto);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) productRepository.count();
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity entity = productRepository.findOne(id);
        return productConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO dto) {
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(dto.getCategoryCode());
        ProductEntity productEntity = new ProductEntity();
        if (dto.getId() != null) {
            ProductEntity oldProduct = productRepository.findOne(dto.getId());
            oldProduct.setCategory(categoryEntity);
            if (dto.getAvatar() == null) {
                dto.setAvatar(oldProduct.getAvatar());
            } else {
                File file = new File(context.getRealPath("/template/images/product") + File.separator + oldProduct.getAvatar());
                if (file.delete()) {
                    System.out.println("Xóa thành công");
                } else {
                    System.out.println("Xóa thất bại");
                }
            }
            productEntity = productConverter.toEntity(oldProduct, dto);
        } else {
            productEntity = productConverter.toEntity(dto);
            productEntity.setCategory(categoryEntity);
        }
        return productConverter.toDTO(productRepository.save(productEntity));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            File file = new File(context.getRealPath("/template/images/product") + File.separator + productRepository.findOne(id).getAvatar());
            if (file.delete()) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
            productRepository.delete(id);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public List<ProductDTO> findByIdCart(long id) {
        String sql = "SELECT b.product_id, b.quantity FROM dbo.cart a JOIN dbo.cart_product b ON b.cart_id = a.id WHERE a.id = " + id;
        List<ProductDTO> products = new ArrayList<>();
        try {
            products = jdbcTemplate.query(sql, new Object[]{}, new RowMapper<ProductDTO>() {
                @Override
                public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ProductEntity productEntity = productRepository.findOne(rs.getLong("product_id"));
                    ProductDTO productDTO = productConverter.toDTO(productEntity);
                    productDTO.setQuantity(rs.getLong("quantity"));

                    return productDTO;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        List<ProductDTO> products = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findByNameContaining(name);
        for (ProductEntity entity : entities) {
            products.add(productConverter.toDTO(entity));
        }
        return products;
    }
}