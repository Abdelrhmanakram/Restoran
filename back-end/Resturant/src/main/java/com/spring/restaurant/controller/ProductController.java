package com.spring.restaurant.controller;

import com.spring.restaurant.service.ProductService;
import com.spring.restaurant.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/categoryId/{categoryId}")
    ResponseEntity<List<ProductDto>> productAllProducts(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }

    @GetMapping("/search/{letters}")
    ResponseEntity<List<ProductDto>> search(@PathVariable("letters") String Letter) {
        return ResponseEntity.ok(productService.getProductByLetters(Letter));
    }

}
