package com.joseca.primer_parcial.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestSellerDTO {
    private String sellerName;

    private Long sellerId;

    private BigDecimal totalSold = new BigDecimal("0");
}
