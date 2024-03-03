package com.joseca.ubicua.repository;

import com.joseca.ubicua.entity.Address;
import com.joseca.ubicua.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class OneToOneMappingTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveMethod() {
        Order order = new Order();
        // Creamos el order
        order.setOrderTrackingNumber("100ABC");
        order.setTotalQuantity(5);
        order.setTotalPrice(new BigDecimal(100));
        order.setStatus("in process");

        Address address = new Address();
        // Creamos el address
        address.setCity("Santa Cruz de la Sierra");
        address.setCountry("Bolivia");
        address.setStreet("Calle Los Chinos");
        address.setZipCode("0000");
        address.setState("Santa Cruz");

        orderRepository.save(order);
    }

    @Test
    void updateMethod() {
        Optional<Order> optionalOrder = orderRepository.findById(2L);

        if(optionalOrder.isEmpty()) {
            System.out.println("No se encontró la orden");
            return;
        }

        Order order = optionalOrder.get();

        order.setStatus("entregado");

        orderRepository.save(order);
    }

    @Test
    void deleteMethod() {
        orderRepository.deleteById(2L);
    }
}
