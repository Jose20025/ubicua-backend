package com.joseca.primer_parcial.entities;

import com.joseca.primer_parcial.repositories.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SellerTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void testCreate3Sellers() {
        Seller seller1 = new Seller();

        seller1.setName("Juan");
        seller1.setLastName("Perez");
        seller1.setEmail("juanperez@gmail.com");

        Seller seller2 = new Seller();

        seller2.setName("Maria");
        seller2.setLastName("Lopez");
        seller2.setEmail("marialopez@gmail.com");

        Seller seller3 = new Seller();

        seller3.setName("Pedro");
        seller3.setLastName("Gomez");
        seller3.setEmail("pedrogomez@gmail.com");

        sellerRepository.saveAll(List.of(seller1, seller2, seller3));
    }
}