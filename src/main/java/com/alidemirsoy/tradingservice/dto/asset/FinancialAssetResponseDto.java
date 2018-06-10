package com.alidemirsoy.tradingservice.dto.asset;

import com.alidemirsoy.tradingservice.dto.BaseResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class FinancialAssetResponseDto extends BaseResponseDto {

    private Long id;

    private String symbol;

    private BigDecimal rate;

    private BigDecimal bid;

    private BigDecimal offer;

    private Double spread;
}
