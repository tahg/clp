package com.revature.ecommerce.services;

import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.repositories.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {
    
    @Test
    void getAll() {
        List<Product> products = ProductService.getAll();
        assert (products.size() == 3);
        System.out.println(products);
    }

    @Test
    void getByIdFound() {
        Product product = ProductService.getById(2L);
        assertNotNull(product);
        assertEquals (product.getName(), "Deluxe SWAG");
    }
    
    @Test
    void getByIdNotFound() {
        Product product = ProductService.getById(4L);
        assertNull(product);
    }
}