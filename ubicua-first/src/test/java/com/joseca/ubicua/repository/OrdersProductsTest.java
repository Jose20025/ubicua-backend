package com.joseca.ubicua.repository;

import com.joseca.ubicua.entity.Address;
import com.joseca.ubicua.entity.Order;
import com.joseca.ubicua.entity.OrderProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OrdersProductsTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrderMethod() {
        Order order = new Order();

        order.setOrderTrackingNumber("100ABC");
        order.setStatus("in progress");

        // Crear los items

        // Crear el primer item
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setProduct(productRepository.findById(1L).get());
        orderProduct1.setQuantity(2);
        orderProduct1.setPrice(orderProduct1
                .getProduct()
                .getPrice()
                .multiply(new BigDecimal(2)));
        orderProduct1.setImageUrl(orderProduct1.getProduct().getImageUrl());
        order.getOrderProducts().add(orderProduct1);

        // Crear el segundo item
        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setProduct(productRepository.findById(2L).get());
        orderProduct2.setQuantity(3);
        orderProduct2.setPrice(orderProduct2
                .getProduct()
                .getPrice()
                .multiply(new BigDecimal(3)));
        orderProduct2.setImageUrl(orderProduct2.getProduct().getImageUrl());
        order.getOrderProducts().add(orderProduct2);

        // Conseguir el total de pedidos en plata
        order.setTotalPrice(order.getTotalPrice());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("Santa Cruz de la Sierra");
        address.setCountry("Bolivia");
        address.setStreet("Barrio Los Chinos");
        address.setState("Santa Cruz");
        address.setZipCode("00000");

        order.setBillingAddress(address);

        orderRepository.save(order);
    }
}
