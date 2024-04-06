package com.joseca.primer_parcial.repositories;

import com.joseca.primer_parcial.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findBySellerId(Long sellerId);
}
