package com.alidemirsoy.tradingservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

/**
 * Reads the list of symbols and the initial market values from configuration
 * @author Ali Demirsoy
 */
@Data
@Component
@ConfigurationProperties("initialize")
public class InitialMarketDataProperties {

    private List<Asset> assets;

    @AllArgsConstructor
    @Data
    public static class Asset {
        private String symbol;

        @DecimalMin(value = "1", inclusive = false)
        private BigDecimal rate;

        @Max(1)
        @Min(0)
        private Double spread;
    }
}
