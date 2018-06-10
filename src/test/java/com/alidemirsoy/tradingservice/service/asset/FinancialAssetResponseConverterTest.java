package com.alidemirsoy.tradingservice.service.asset;

import com.alidemirsoy.tradingservice.domain.FinancialAsset;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class FinancialAssetResponseConverterTest {

    @InjectMocks
    FinancialAssetResponseConverter converter;

    @Test
    public void shouldConvertToResponseDto() {
        //Given
        FinancialAsset asset = new FinancialAsset();
        asset.setId(5L);
        asset.setSymbol("AAL.L");
        asset.setSpread(0.5);
        asset.setRate(new BigDecimal("100"));

        //When
        FinancialAssetResponseDto actual = converter.convert(asset);

        //Then
        assertThat(actual.getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.getSpread()).isEqualTo(0.5);
        assertThat(actual.getRate()).isEqualTo(new BigDecimal("100"));
        assertThat(actual.getBid()).isEqualTo(new BigDecimal("99.5"));
        assertThat(actual.getOffer()).isEqualTo(new BigDecimal("100.5"));
    }
}