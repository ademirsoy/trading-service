package com.alidemirsoy.tradingservice;

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

    @Override
    public void run(ApplicationArguments args) {
        //TODO javadoc yaz
        if (financialAssetRepository.count() == 0) {
            initialMarketDataProperties.getAssets().forEach(this::insertIfNotExists);
        }
    }

    private void insertIfNotExists(InitialMarketDataProperties.Asset asset) {
        try {
            financialAssetRepository.insert(asset.getSymbol(), asset.getRate(), asset.getSpread());
        } catch (Exception e) {
            log.warn("Asset table is already initialized!", e);
        }
    }
}
