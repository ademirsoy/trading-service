package com.alidemirsoy.tradingservice.controller;

import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetCreateRequestDto;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetResponseDto;
import com.alidemirsoy.tradingservice.dto.asset.SpreadUpdateRequestDto;
import com.alidemirsoy.tradingservice.service.asset.FinancialAssetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FinancialAssetControllerTest {

    @InjectMocks
    FinancialAssetController financialAssetController;

    @Mock
    FinancialAssetService financialAssetService;

    @Test
    public void shouldGetAllAssets() {
        //Given
        FinancialAssetResponseDto responseDto = new FinancialAssetResponseDto();
        responseDto.setSymbol("AAL.L");
        when(financialAssetService.getAllAssets()).thenReturn(Collections.singletonList(responseDto));

        //When
        Iterable<FinancialAssetResponseDto> actual = financialAssetController.getAll();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.iterator().next().getSymbol()).isEqualTo("AAL.L");
    }

    @Test
    public void shouldCreateNewAsset() {
        //Given
        FinancialAssetCreateRequestDto createRequest = new FinancialAssetCreateRequestDto();
        createRequest.setSymbol("AAL.L");
        createRequest.setRate(new BigDecimal("100"));
        createRequest.setSpread(0.5);

        FinancialAssetResponseDto responseDto = new FinancialAssetResponseDto();
        responseDto.setSymbol("AAL.L");
        responseDto.setRate(new BigDecimal("100"));
        responseDto.setSpread(0.5);
        when(financialAssetService.createNewAsset(createRequest)).thenReturn(responseDto);

        //When
        FinancialAssetResponseDto actual = financialAssetController.create(createRequest);

        //Then
        assertThat(actual.getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.getRate()).isEqualTo(new BigDecimal("100"));
        assertThat(actual.getSpread()).isEqualTo(0.5);
    }

    @Test
    public void shouldUpdateSpread() {
        //Given
        SpreadUpdateRequestDto updateRequestDto = new SpreadUpdateRequestDto();
        updateRequestDto.setId(10L);
        updateRequestDto.setSpread(0.5);

        FinancialAssetResponseDto responseDto = new FinancialAssetResponseDto();
        responseDto.setSymbol("AAL.L");
        responseDto.setId(10L);
        responseDto.setSpread(0.5);
        when(financialAssetService.updateSpread(10L, updateRequestDto)).thenReturn(responseDto);

        //When
        FinancialAssetResponseDto actual = financialAssetController.updateSpread(updateRequestDto, 10L);

        //Then
        assertThat(actual.getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.getId()).isEqualTo(10L);
        assertThat(actual.getSpread()).isEqualTo(0.5);
    }
}