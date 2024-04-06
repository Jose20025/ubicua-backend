package com.joseca.primer_parcial.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBuyDTO {
    private Long clientId;

    private String clientName;

    private BigDecimal totalPurchased = new BigDecimal("0");
}
