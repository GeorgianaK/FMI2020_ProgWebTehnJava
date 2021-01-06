package com.sal.java.service;

import com.sal.java.model.Product;
import com.sal.java.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void decrementStock(Product product, int quantity) {
        product.setAvailableStock(product.getAvailableStock() - quantity);
        productRepository.save(product);
    }

    public void saveProducts(List<Product> products) {
        for(Product prod: products) {
            productRepository.save(prod);
        }
    }
}
