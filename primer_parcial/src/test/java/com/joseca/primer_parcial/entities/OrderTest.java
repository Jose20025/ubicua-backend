package com.joseca.primer_parcial.entities;

import com.joseca.primer_parcial.repositories.ClientRepository;
import com.joseca.primer_parcial.repositories.OrderProductRepository;
import com.joseca.primer_parcial.repositories.OrderRepository;
import com.joseca.primer_parcial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Test
    public void testCreateOrder() {
        Client client = clientRepository.findById(1L).orElse(null);

        Product product1 = productRepository.findByName("Peluca");
        Product product2 = productRepository.findByName("Panel de Aluminio");
//        Product product3 = productRepository.findByName("Peluca");

        Order order = new Order();
        order.setClient(client);
        order.setDate(LocalDate.now());
        order.setStatus("PENDIENTE");
        order.setTotal(new BigDecimal(0));

        Order createdOrder = orderRepository.save(order);

        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setOrder(createdOrder);
        orderProduct1.setProduct(product1);
        orderProduct1.setQuantity(3600);

        if (orderProduct1.getQuantity() > product1.getStock()) {
            throw new RuntimeException("No hay suficiente stock");
        }

        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setOrder(createdOrder);
        orderProduct2.setProduct(product2);
        orderProduct2.setQuantity(2);

        if (orderProduct2.getQuantity() > product2.getStock()) {
            throw new RuntimeException("No hay suficiente stock");
        }

//        OrderProduct orderProduct3 = new OrderProduct();
//        orderProduct3.setOrder(createdOrder);
//        orderProduct3.setProduct(product3);
//        orderProduct3.setQuantity(3);

        orderProductRepository.saveAll(List.of(orderProduct1, orderProduct2));

        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(createdOrder.getId());

        orderProducts.forEach(orderProduct -> createdOrder.addProductPriceToTotal(orderProduct.getProduct(), orderProduct.getQuantity()));


        orderRepository.save(createdOrder);
    }

    @Test
    public void testGetOrderProductsByOrderId() {
        Order order = orderRepository.findById(3L).get();

        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(order.getId());

        System.out.println(order.getTotal());
        System.out.println("============================");
        orderProducts.forEach(orderProduct -> {
            System.out.println("Producto: " + orderProduct.getProduct().getName());
            System.out.println("Cantidad: " + orderProduct.getQuantity());
            System.out.println("Precio: " + orderProduct.getProduct().getPrice());
            System.out.println("Subtotal: " + orderProduct.getProduct().getPrice().multiply(new BigDecimal(orderProduct.getQuantity())));
            System.out.println("============================");
        });
    }
}