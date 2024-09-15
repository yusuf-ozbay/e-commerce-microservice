package com.javaacademy.stock.service;

import com.javaacademy.stock.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto getProduct(String id);
    ProductDto update(ProductDto productDto, String id);
    void delete(String id);

    List<ProductDto> getAllProducts();
}
