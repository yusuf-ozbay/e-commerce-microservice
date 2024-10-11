package com.javaacademy.basket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Basket{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String basketId;
    private double totalAmount;
    private int status;
    private String userId;
    @OneToMany(mappedBy = "basket" , cascade = CascadeType.ALL)
    private List<BasketItem> basketItemList;
}