package com.javaacademy.basket.library;

import com.javaacademy.basket.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "STOCK")
public interface StockClient {

    @GetMapping("/product/{id}")
    ProductDto getProductById(@PathVariable("id") String id);

}
