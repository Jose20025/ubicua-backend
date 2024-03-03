package com.joseca.ubicua.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    private BigDecimal price;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
