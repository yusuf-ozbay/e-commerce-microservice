package com.javaacademy.stock.service;

import com.javaacademy.stock.dto.CategoryDto;
import com.javaacademy.stock.entity.Category;

import java.util.List;


public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto getCategory(String id);
    List<CategoryDto> getAllCategory();
    CategoryDto update(CategoryDto categoryDto , String id);

    Category getCategoryEntity(String id);
    void delete(String id);
}
