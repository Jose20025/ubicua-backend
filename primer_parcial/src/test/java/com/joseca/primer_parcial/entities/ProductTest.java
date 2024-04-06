package com.joseca.primer_parcial.entities;

import com.joseca.primer_parcial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ProductTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreate3Products() {
        Product product1 = new Product();
        product1.setName("Panel de Aluminio");
        product1.setStock(50);
        product1.setPrice(new BigDecimal("500"));
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Peluca");
        product2.setStock(5);
        product2.setPrice(new BigDecimal("350"));
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Cámara de Seguridad");
        product3.setStock(30);
        product3.setPrice(new BigDecimal("1500"));
        productRepository.save(product3);
    }
}