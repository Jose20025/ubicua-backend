package com.joseca.primer_parcial.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestClientSellerDTO {
    private String clientName;

    private String sellerName;

    private BigDecimal totalPurchased = new BigDecimal("0");
}
