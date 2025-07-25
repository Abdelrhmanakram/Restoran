package com.spring.restaurant.service.Impl;

import com.spring.restaurant.controller.vm.ProductResponseVM;
import com.spring.restaurant.mapper.ProductMapper;
import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.Product;
import com.spring.restaurant.repository.CategoryRepository;
import com.spring.restaurant.repository.ProductRepository;
import com.spring.restaurant.service.ProductService;
import com.spring.restaurant.service.dto.ProductDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponseVM getProductsByCategoryId(Long categoryId , Integer pageNo , Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.findAllByCategoryId(categoryId, pageable);
        return  new ProductResponseVM(
                ProductMapper.PRODUCT_MAPPER.toDtoList(productPage.getContent()),
                productPage.getTotalElements()
        );
    }

    @Override
    public ProductResponseVM getAllProducts(Integer pageNo , Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
        return  new ProductResponseVM(
                ProductMapper.PRODUCT_MAPPER.toDtoList(productPage.getContent()),
                productPage.getTotalElements()
        );

    }

    @Override
    public ProductResponseVM getProductByLetters(String letter , Integer pageNo , Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.getProductByLetters(letter, pageable);
        List<Product> productList = productPage.getContent();

        if (productList.isEmpty()) {
            throw  new RuntimeException("error.noSuchLetter");
        }

        return  new ProductResponseVM(
                ProductMapper.PRODUCT_MAPPER.toDtoList(productList),
                productPage.getTotalElements()
        );
    }

    @Override
    public List<ProductDto> findProductsByIds(List<Long> porductIds) {
        List<Product> productList = productRepository.findAllById(porductIds);
        return  ProductMapper.PRODUCT_MAPPER.toDtoList(productList);
    }

    @Override
    public Product addProduct(ProductDto pDto) {

        Product product = new Product();
        product.setDescription(pDto.getDescription());
        product.setPrice(pDto.getPrice());
        product.setCategory(categoryRepository.findById(pDto.getCategoryId()).get());
        product.setName(pDto.getName());
        product.setLogoPath(pDto.getLogoPath());


        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto pDto , Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (pDto.getName() != null){
            product.setName(pDto.getName());
        }
        if (pDto.getLogoPath() != null){
            product.setLogoPath(pDto.getLogoPath());
        }
        if (pDto.getDescription() != null) {
            product.setDescription(pDto.getDescription());
        }
        if (pDto.getPrice() > 0) {
            product.setPrice(pDto.getPrice());
        }
        if (pDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(pDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

         productRepository.delete(product);
    }

}
