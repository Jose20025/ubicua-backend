package com.joseca.primer_parcial.reports;

import com.joseca.primer_parcial.DTO.BestSellerDTO;
import com.joseca.primer_parcial.entities.Client;
import com.joseca.primer_parcial.entities.Order;
import com.joseca.primer_parcial.entities.Seller;
import com.joseca.primer_parcial.repositories.ClientRepository;
import com.joseca.primer_parcial.repositories.OrderRepository;
import com.joseca.primer_parcial.repositories.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BestSellerTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void testGetBestSeller() {
        List<Seller> allSellers = sellerRepository.findAll();

        List<BestSellerDTO> sellers = new ArrayList<>();

        allSellers.forEach(seller -> {
            List<Client> clients = clientRepository.findBySellerId(seller.getId());

            BestSellerDTO bestSeller = new BestSellerDTO();

            bestSeller.setSellerId(seller.getId());
            bestSeller.setSellerName(seller.getName());

            clients.forEach(client -> {
                List<Order> orders = orderRepository.findByClientId(client.getId());

                orders.forEach(order -> {
                    bestSeller.setTotalSold(bestSeller.getTotalSold().add(order.getTotal()));
                });
            });

            sellers.add(bestSeller);
        });

        BestSellerDTO bestSeller = new BestSellerDTO();

        sellers.forEach(seller -> {
            if (seller.getTotalSold().compareTo(bestSeller.getTotalSold()) > 0) {
                bestSeller.setSellerId(seller.getSellerId());
                bestSeller.setSellerName(seller.getSellerName());
                bestSeller.setTotalSold(seller.getTotalSold());
            }
        });

        System.out.println("==== MEJOR VENDEDOR ====");
        System.out.println("ID: " + bestSeller.getSellerId());
        System.out.println("Nombre: " + bestSeller.getSellerName());
        System.out.println("Total vendido: " + bestSeller.getTotalSold());
        System.out.println("========================");
    }
}
