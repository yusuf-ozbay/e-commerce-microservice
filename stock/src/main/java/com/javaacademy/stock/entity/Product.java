package com.javaacademy.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;
    private String name;
    private int stock;
    private double price;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "categoryId")
    private Category category;


}