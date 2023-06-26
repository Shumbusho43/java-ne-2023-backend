package com.ne.java.services;

import com.ne.java.dtos.CreateProductDto;
import com.ne.java.models.Product;
import com.ne.java.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product registerProduct(@Valid CreateProductDto product) {
        Product entity = new Product();
        entity.setName(product.getName());
        entity.setCode(product.getCode());
        entity.setProductType(product.getProductType());
        entity.setPrice(product.getPrice());
        return productRepository.save(entity);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}