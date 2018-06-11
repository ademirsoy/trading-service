package com.alidemirsoy.tradingservice.init;

import com.alidemirsoy.tradingservice.configuration.InitialMarketDataProperties;
import com.alidemirsoy.tradingservice.repository.FinancialAssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    FinancialAssetRepository financialAssetRepository;

    @Autowired
    InitialMarketDataProperties initialMarketDataProperties;

    @Autowired
    ModelMapper modelMapper;

    /**
     * This method is executed only once during start-up
     * and reads market information from the configuration,
     * initializes randomly generated rate values and persist to database
     * If the data is already initialized, then does nothing.
     * @param args Unused.
     * @author Ali Demirsoy
     */
    @Override
    public void run(ApplicationArguments args) {
        if (financialAssetRepository.count() == 0) {
            initialMarketDataProperties.getAssets().forEach(this::insertIfNotExists);
        }
    }

    private void insertIfNotExists(InitialMarketDataProperties.Asset asset) {
        try {
            log.info("Initial data: {}, {}, {}", asset.getSymbol(), asset.getRate(), asset.getSpread());
            //Rate value is provided from the configuration as a random integer.
            financialAssetRepository.insert(asset.getSymbol(), asset.getRate(), asset.getSpread());
        } catch (Exception e) {
            log.warn("Asset table is already initialized!", e);
        }
    }
}
