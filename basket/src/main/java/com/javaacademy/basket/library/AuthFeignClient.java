package com.javaacademy.basket.library;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "auth")
public interface AuthFeignClient extends AuthCallableFeignClient {


}
