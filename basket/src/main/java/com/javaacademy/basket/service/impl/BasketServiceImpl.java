package com.javaacademy.basket.service.impl;

import com.javaacademy.basket.dto.BasketDto;
import com.javaacademy.basket.dto.BasketItemDto;
import com.javaacademy.basket.dto.ProductDto;
import com.javaacademy.basket.dto.UserDto;
import com.javaacademy.basket.entity.Basket;
import com.javaacademy.basket.entity.BasketItem;
import com.javaacademy.basket.exception.ResourceNotFoundException;
import com.javaacademy.basket.repository.BasketRepository;
import com.javaacademy.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private static final String authUrl = "http://localhost:9091/auth/users/";
    public final int BASKET_STATUS_NONE = 0;


    private final BasketRepository repository;
    private final BasketItemServiceImpl basketItemService;
    private final RestTemplate restTemplate;



    @Override
    public BasketDto saveProductToBasket(BasketDto basketDto) {
        Basket basket = repository.findBasketByUserIdAndStatusEquals(basketDto.getUserId(), BASKET_STATUS_NONE);
        if (basket != null) {
            // Basket Var Senaryosu
            return sepetVarUrunEkle(basket, basketDto);
        } else {
            // Basket yok senaryosu
            return sepetYokYeniSepetOlustur(basketDto);
        }

    }

    public UserDto findByUserId(BasketDto basketDto){
        UserDto userDto=restTemplate.getForObject(authUrl + basketDto.getUserId(), UserDto.class);
        return userDto;
    }


    @Override
    public BasketDto findById(String id) {
        Basket basket = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Basket not found with id:" + id));
        ;
        return toDto(basket);

    }


    @Override
    public BasketDto deleteProduct(String basketId, String productId) {
        Basket basket = repository.findById(basketId)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found with id: " + basketId));

        List<BasketItem> basketItems = basket.getBasketItemList();
        BasketItem basketItem = basketItemService.findBasketItemByBasketIdAndProductId(basketId, productId);

        if (basketItem != null) {
            basketItems.remove(basketItem);
            basketItemService.delete(basketItem);
            basket.setBasketItemList(basketItems);
            basket.setTotalAmount(calculateBasketAmount(basket.getBasketItemList()));
            repository.save(basket);
            return toDto(basket);
        } else {
            throw new RuntimeException("Product not found in basket");
        }
    }



    private BasketDto sepetYokYeniSepetOlustur(BasketDto basketDto) {                 //bu var
        Basket basket = new Basket();
        UserDto user=restTemplate.getForObject(authUrl + basketDto.getUserId(), UserDto.class);

        basket.setUserId(user.getUserId());
        basket.setStatus(BASKET_STATUS_NONE);
        List<BasketItem> basketItemList = new ArrayList<>();
        ProductDto product = basketItemService.getProductById(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));

        for (BasketItemDto basketItemDto : basketDto.getBasketItemList()) {
            BasketItem basketItem = new BasketItem();
            basketItem.setProductId(product.getProductId());
            basketItem.setCount(basketItemDto.getCount());
            basketItem.setBasketItemAmount(basketItem.getCount() * product.getPrice());
            basketItem.setBasket(basket);
            basketItemList.add(basketItem);
//          basketItemList.add(basketItem);
//          Yukarıdaki kod satırını neden çalıştırmadı?
        }
        basket.setBasketItemList(basketItemList);
        basket.setTotalAmount(basket.getBasketItemList().get(0).getCount() * product.getPrice());

//        basket = repository.save(basket);
        //      basket.setTotalAmount(calculateBasketAmount(basket.getBasketId()));


        basket = repository.save(basket);

        return toDto(basket);
    }

    private BasketDto sepetVarUrunEkle(Basket basket, BasketDto basketDto) {                    //bu var
        List<BasketItem> basketItemList = basket.getBasketItemList();
        // Aşağıdaki kod satırı yerine gelen listeyi for ile dönebilirdik fakat bunu yapmak yerine repository'e metod yazıp oradan var mı yok mu kontrolünü yapmak bestPractice.
        BasketItem basketItem = basketItemService.findBasketItemByBasketIdAndProductId(basket.getBasketId(), basketDto.getBasketItemList().get(0).getProduct().getProductId());

        // Aynı üründen mi ekleme yapmış farklı üründen mi ekleme yapmış

        // Farklı üründen ekleme yapma senaryosu
        if (basketItem == null) {
            System.out.println("Eklenen ürün sepette yok senaryosu");
            BasketItem newBasketItem = new BasketItem();

            // Product product = basketItem.getProduct(); Hoca bunu yazdı yüksek ihtimalle yanlış var!
            ProductDto productDto = basketItemService.getProductById(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            newBasketItem.setProductId(productDto.getProductId());
            newBasketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            newBasketItem.setBasketItemAmount(basketDto.getBasketItemList().get(0).getCount() * productDto.getPrice());
            newBasketItem.setBasket(basket);
//            newBasketItem = basketItemService.save(newBasketItem);
            basketItemList.add(newBasketItem);

        }
        // Ayın üründen ürün  ekleme senaryosu
        else {
            System.out.println("Eklenen ürün sepette var senaryosu");
            System.out.println("liste var mı " + basketDto.getBasketItemList());
            System.out.println("BasketİtemList boş mu" + basketDto.getBasketItemList().get(0).getProduct().getName());
            System.out.println("BasketItem : " + basketItem);
            // Eklenen ürün sepette var
            ProductDto productDto = basketItemService.getProductById(String.valueOf(basketItem.getProductId()));
            basketItem.setProductId(productDto.getProductId());
            basketItem.setCount(basketDto.getBasketItemList().get(0).getCount() + basketItem.getCount());
            basketItem.setBasketItemId(basket.getBasketItemList().get(0).getBasketItemId());
            basketItem.setBasketItemAmount(basketItem.getCount() * productDto.getPrice());

        }

        basket.setTotalAmount(calculateBasketAmount(basket.getBasketItemList()));
        basket.setBasketItemList(basketItemList);
        repository.save(basket);

        return toDto(basket);

    }

    private double calculateBasketAmount(List<BasketItem> basketItems) {                                  //sepet tutarını hesaplıyor
        double totalAmount = 0;
        for (BasketItem basketItem : basketItems) {
            totalAmount += basketItem.getBasketItemAmount();
        }
        return totalAmount;
    }



    // Response'tan önce çalışacak olan metod
    public BasketDto toDto (Basket basket) {
        return BasketDto.builder()
                .basketId(basket.getBasketId())
                .totalAmount(basket.getTotalAmount())
                .userId(basket.getUserId())
                .status(basket.getStatus())
                .basketItemList(basket.getBasketItemList().stream()
                        .map(basketItem -> basketItemService.toDto(basketItem))
                        .collect(Collectors.toList()))
                .build();
    }
}
