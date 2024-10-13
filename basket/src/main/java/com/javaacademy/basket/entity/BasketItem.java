package com.javaacademy.basket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false, length = 36)
    private String basketItemId;
    private String productId;
    private int count;
    private double basketItemAmount;
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;
}
