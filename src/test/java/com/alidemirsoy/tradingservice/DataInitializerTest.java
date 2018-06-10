package com.alidemirsoy.tradingservice;

import com.alidemirsoy.tradingservice.configuration.InitialMarketDataProperties;
import com.alidemirsoy.tradingservice.init.DataInitializer;
import com.alidemirsoy.tradingservice.repository.FinancialAssetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataInitializerTest {

    @InjectMocks
    DataInitializer dataInitializer;

    @Mock
    FinancialAssetRepository financialAssetRepository;

    @Mock
    InitialMarketDataProperties initialMarketDataProperties;

    @Test
    public void shouldInsertDataWhenTableEmpty() {
        //Given
        when(financialAssetRepository.count()).thenReturn(0L);
        InitialMarketDataProperties.Asset asset1 = new InitialMarketDataProperties.Asset("AAL.L", new BigDecimal("10"), 1.5);
        InitialMarketDataProperties.Asset asset2 = new InitialMarketDataProperties.Asset("BP.L", new BigDecimal("20"), 2.5);
        when(initialMarketDataProperties.getAssets()).thenReturn(Arrays.asList(asset1, asset2));

        //When
        dataInitializer.run(null);

        //Then
        verify((financialAssetRepository)).insert("AAL.L", new BigDecimal("10"), 1.5);
        verify((financialAssetRepository)).insert("BP.L", new BigDecimal("20"), 2.5);
    }

    @Test
    public void shouldNotInsertDataWhenTableAlreadyInitialized() {
        //Given
        when(financialAssetRepository.count()).thenReturn(10L);
        //When
        dataInitializer.run(null);

        //Then
        verify(financialAssetRepository, times(0)).insert(anyString(), any(BigDecimal.class), anyDouble());
    }
}