package com.alidemirsoy.tradingservice.dto.asset;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Data
public class SpreadUpdateRequestDto {

    private Long id;

    @Min(value = 0, message = "Spread should be 0 or larger")
    private Double spread;
}
