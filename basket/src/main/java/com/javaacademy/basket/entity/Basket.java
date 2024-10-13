package com.javaacademy.basket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Basket{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false, length = 36)
    private String basketId;
    private double totalAmount;
    private int status;
    private String userId;
    @OneToMany(mappedBy = "basket" , cascade = CascadeType.ALL)
    private List<BasketItem> basketItemList;
}