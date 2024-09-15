package com.javaacademy.stock.controller;

import com.javaacademy.stock.dto.CategoryDto;
import com.javaacademy.stock.request.CategoryRequest;
import com.javaacademy.stock.response.CategoryResponse;
import com.javaacademy.stock.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    private final  CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(toResponse(service.save(categoryRequest.toDto())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id){
        return ResponseEntity.ok(toResponse(service.getCategory(id)));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> responseList  = service.getAllCategory().stream()
                .map(categoryDto -> toResponse(categoryDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest categoryRequest , @PathVariable String id){
        return ResponseEntity.ok(toResponse(service.update(categoryRequest.toDto() , id)));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Category deleted successfuly";
    }

    public CategoryResponse toResponse(CategoryDto categoryDto){
        return CategoryResponse.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .build();
    }
    
    
}
