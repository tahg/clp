package com.revature.ecommerce.controllers;

import com.revature.ecommerce.exceptions.NetworkException;
import com.revature.ecommerce.models.Product;
import com.revature.ecommerce.services.ProductService;
import com.revature.ecommerce.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    @CrossOrigin
    @GetMapping(value = "/products")
    @ResponseBody List<Product> productList(HttpServletRequest request, HttpServletResponse response) {
        try {
            TokenService.extractRequesterDetails(request);
            return ProductService.getAll();
        } catch (NetworkException e) {
            response.setStatus(e.getStatusCode());
            return null;
        }
    }

    @CrossOrigin
    @PostMapping(value = "/order")
    @ResponseBody String order(HttpServletRequest request, @RequestParam(value = "id") Long id, HttpServletResponse response) {
        try {
            TokenService.extractRequesterDetails(request);
            Product product = ProductService.getById(id);
            if (product != null) return "Thank you for ordering " + product.getName() + ".";
            else return "No product with an id of " + id + " could be found.";
        } catch (NetworkException e) {
            response.setStatus(e.getStatusCode());
            return null;
        }
    }
}
