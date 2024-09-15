package com.javaacademy.stock.service.impl;

import com.javaacademy.stock.dto.CategoryDto;
import com.javaacademy.stock.dto.ProductDto;
import com.javaacademy.stock.entity.Category;
import com.javaacademy.stock.entity.Product;
import com.javaacademy.stock.exception.ResourceNotFoundException;
import com.javaacademy.stock.repository.ProductRepository;
import com.javaacademy.stock.service.CategoryService;
import com.javaacademy.stock.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private CategoryService categoryService;
    private ProductRepository repository;



    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = toEntity(productDto);
        product = repository.save(product);

        return toDto(product);
    }

    @Override
    public ProductDto getProduct(String id) {
        Product product = repository.findById(Long.valueOf(id)).get();

        return toDto(product);
    }

    @Override
    public ProductDto update(ProductDto productDto, String id) {
        Product product = repository.findById(Long.valueOf(id)).get();
        product.setStock(productDto.getStock());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.getCategoryEntity(String.valueOf(productDto.getCategoryId())));
        product = repository.save(product);
        return toDto(product);
    }

    @Override
    public void delete(String id) {
        Product product = repository.findById(Long.valueOf(id)).get();
        repository.delete(product);

    }

    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll().stream()
                .map(product -> toDto(product))
                .collect(Collectors.toList());
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .price(product.getPrice())
                .productId(product.getProductId())
                .name(product.getName())
                .stock(product.getStock())
                .categoryId(product.getCategory().getCategoryId())
                .build();
    }

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(categoryService.getCategoryEntity(String.valueOf(productDto.getCategoryId())));
        return product;

    }
}
