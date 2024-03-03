package com.joseca.ubicua.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders", uniqueConstraints = {
        @UniqueConstraint(columnNames = "order_tracking_number", name = "otn_unique")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_tracking_number", nullable = false)
    private String orderTrackingNumber;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;

    // Por defecto el FetchType es Lazy
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public BigDecimal getTotalPrice() {
        BigDecimal amount = new BigDecimal("0.0");

        for (OrderProduct item : this.orderProducts) {
            amount = amount.add(item.getPrice());
        }

        return amount;
    }
}
