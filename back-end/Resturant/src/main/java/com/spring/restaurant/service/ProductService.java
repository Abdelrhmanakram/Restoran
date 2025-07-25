package com.spring.restaurant.service;

import com.spring.restaurant.controller.vm.ProductResponseVM;
import com.spring.restaurant.model.Product;
import com.spring.restaurant.service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductResponseVM getProductsByCategoryId(Long categoryId , Integer pageNo , Integer pageSize);

    ProductResponseVM getAllProducts(Integer pageNo , Integer pageSize);

    ProductResponseVM getProductByLetters(String letter , Integer pageNo , Integer pageSize);

    List<ProductDto> findProductsByIds(List<Long> porductIds);

    Product addProduct(ProductDto pDto );

    Product updateProduct(ProductDto pDto , Long id );

    void deleteProduct(Long id);

}
