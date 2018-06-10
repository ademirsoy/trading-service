package com.alidemirsoy.tradingservice.dto.asset;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class FinancialAssetCreateRequestDto {

    private Long id;

    private String symbol;

    @Min(value = 0, message = "Asset should be a positive value")
    private BigDecimal rate;

    @Min(value = 0, message = "Spread should be 0 or larger")
    private Double spread;
}
