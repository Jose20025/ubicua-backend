package com.joseca.primer_parcial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal total = new BigDecimal(0);

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "id")
    private Client client;

    public void addProductPriceToTotal(Product product, int quantity) {
        this.total = this.total.add(product.getPrice().multiply(new BigDecimal(quantity)));
    }
}
