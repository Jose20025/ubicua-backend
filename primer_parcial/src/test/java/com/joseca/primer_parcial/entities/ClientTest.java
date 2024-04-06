package com.joseca.primer_parcial.entities;

import com.joseca.primer_parcial.repositories.ClientRepository;
import com.joseca.primer_parcial.repositories.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClientTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void testCreate3ClientsWithSeller() {
        Seller seller = sellerRepository.findById(2L).orElse(null);

        Client client1 = new Client();

        client1.setName("Papucho");
        client1.setLastName("Rodriguez");
        client1.setEmail("papuro@gmail.com");
        client1.setCompany("Papucho Rodriguez SRL");
        client1.setPhone("766564474");
        client1.setSeller(seller);

        Client client2 = new Client();

        client2.setName("Mario");
        client2.setLastName("Bros");
        client2.setEmail("mariobros@gmail.com");
        client2.setCompany("Mario Bros SRL");
        client2.setPhone("766554444547");
        client2.setSeller(seller);

        Client client3 = new Client();

        client3.setName("Jorge");
        client3.setLastName("Patichi");
        client3.setEmail("jorpati@gmail.com");
        client3.setCompany("Jorge Patichi SRL");
        client3.setPhone("7665655577");
        client3.setSeller(seller);

        clientRepository.saveAll(List.of(client1, client2, client3));
    }
}