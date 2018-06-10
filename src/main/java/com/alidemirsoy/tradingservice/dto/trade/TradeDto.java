package com.alidemirsoy.tradingservice.dto.trade;

import com.alidemirsoy.tradingservice.domain.TradeType;
import com.alidemirsoy.tradingservice.dto.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradeDto extends BaseResponseDto {

    private Long id;

    @NotNull
    private String symbol;

    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Asset should be a positive value")
    private BigDecimal rate;

    @NotNull
    @Min(value = 1, message = "Amount should be a positive value")
    private Long amount;

    @NotNull
    private TradeType type;
}
