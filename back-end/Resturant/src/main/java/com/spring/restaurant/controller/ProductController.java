package com.spring.restaurant.controller;

import com.spring.restaurant.controller.vm.ProductResponseVM;
import com.spring.restaurant.model.Product;
import com.spring.restaurant.service.ProductService;
import com.spring.restaurant.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
//@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("pageNo/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ProductResponseVM> getAllProducts(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(productService.getAllProducts(pageNo , pageSize));
    }

    @GetMapping("/categoryId/{categoryId}/pageNo/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ProductResponseVM> productAllProducts(@PathVariable("categoryId") Long categoryId , @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId , pageNo , pageSize));
    }

    @GetMapping("/search/{letters}/pageNo/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ProductResponseVM> search(@PathVariable("letters") String Letter , @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(productService.getProductByLetters(Letter , pageNo , pageSize));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product createdProduct = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto , @PathVariable Long id) {
        Product updateProduct = productService.updateProduct(productDto , id);

        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
