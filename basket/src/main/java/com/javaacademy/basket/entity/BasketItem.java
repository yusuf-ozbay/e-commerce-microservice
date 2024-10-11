package com.javaacademy.basket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String basketItemId;
    private String productId;
    private int count;
    private double basketItemAmount;
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;
}
