package com.javaacademy.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false, length = 36)
    private String productId;
    private String name;
    private int stock;
    private double price;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "categoryId")
    private Category category;


}