package com.ne.java.services;

import com.ne.java.dtos.CreateQuantityDto;
import com.ne.java.models.Product;
import com.ne.java.models.Quantity;
import com.ne.java.repositories.ProductRepository;
import com.ne.java.repositories.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityService {
    private final QuantityRepository quantityRepository;
    private final ProductRepository productRepository;

    @Autowired
    public QuantityService(QuantityRepository quantityRepository, ProductRepository productRepository) {
        this.quantityRepository = quantityRepository;
        this.productRepository = productRepository;
    }

    public Quantity registerQuantity(CreateQuantityDto quantityDto) {
        // Retrieve the product by productId
        Product product = productRepository.findById(quantityDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + quantityDto.getProductId()));
        //check if the quantity is negative or zero
        if (quantityDto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        // Create the Quantity entity
        Quantity quantity = new Quantity();
        quantity.setProduct(product);
        quantity.setQuantity(quantityDto.getQuantity());
        quantity.setOperation(quantityDto.getOperation());
        return quantityRepository.save(quantity);
    }
}
