package com.joseca.primer_parcial.reports;

import com.joseca.primer_parcial.DTO.BestClientPerSellerDTO;
import com.joseca.primer_parcial.DTO.BestClientSellerDTO;
import com.joseca.primer_parcial.DTO.ClientBuyDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BestClientPerSellerTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void testGetBestClientPerSeller() {
        List<Seller> allSellers = sellerRepository.findAll();

        BestClientPerSellerDTO bestClientPerSellerDTO = new BestClientPerSellerDTO();

        Map<Long, List<ClientBuyDTO>> clientsPerSeller = new HashMap<>();

        allSellers.forEach(seller -> {
            List<Client> clients = clientRepository.findBySellerId(seller.getId());

            clientsPerSeller.put(seller.getId(), new ArrayList<>());

            clients.forEach(client -> {
                List<Order> orders = orderRepository.findByClientId(client.getId());

                ClientBuyDTO clientBuyDTO = new ClientBuyDTO();
                clientBuyDTO.setClientId(client.getId());
                clientBuyDTO.setClientName(client.getName());

                orders.forEach(order -> {
                    clientBuyDTO.setTotalPurchased(clientBuyDTO.getTotalPurchased().add(order.getTotal()));
                });

                clientsPerSeller.get(seller.getId()).add(clientBuyDTO);
            });
        });

        allSellers.forEach(seller -> {
            List<ClientBuyDTO> clients = clientsPerSeller.get(seller.getId());

            BestClientSellerDTO bestClientSellerDTO = new BestClientSellerDTO();
            bestClientSellerDTO.setSellerName(seller.getName());

            clients.forEach(client -> {
                if (client.getTotalPurchased().compareTo(bestClientSellerDTO.getTotalPurchased()) > 0) {
                    bestClientSellerDTO.setClientName(client.getClientName());
                    bestClientSellerDTO.setTotalPurchased(client.getTotalPurchased());
                }
            });

            bestClientPerSellerDTO.getData().add(bestClientSellerDTO);
        });

        bestClientPerSellerDTO.getData().forEach(bestClientSellerDTO -> {
            System.out.println("==== MEJOR CLIENTE POR VENDEDOR ====");
            System.out.println("Vendedor: " + bestClientSellerDTO.getSellerName());
            System.out.println("Cliente: " + bestClientSellerDTO.getClientName());
            System.out.println("Total comprado: " + bestClientSellerDTO.getTotalPurchased());
            System.out.println("========================");
        });
    }
}
