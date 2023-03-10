package com.revature.ecommerce.services;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private static ProductRepository products;
    
    public ProductService(ProductRepository productRepository) {
        products = productRepository;
    }

    public static List<Product> getAll() {
        return products.findAll();
    }
    public static Product getById(Long id) { 
            return products.findById(id).orElse(null);
    }
}
