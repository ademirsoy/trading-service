package com.alidemirsoy.tradingservice.controller;

import com.alidemirsoy.tradingservice.dto.trade.TradeDto;
import com.alidemirsoy.tradingservice.service.trade.TradeService;
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
public class TradeControllerTest {

    @InjectMocks
    TradeController tradeController;

    @Mock
    TradeService tradeService;

    @Test
    public void shouldGetAllTrades() {
        //Given
        TradeDto responseDto = new TradeDto();
        responseDto.setSymbol("AAL.L");
        when(tradeService.getAllTrades()).thenReturn(Collections.singletonList(responseDto));

        //When
        Iterable<TradeDto> actual = tradeController.getAll();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.iterator().next().getSymbol()).isEqualTo("AAL.L");
    }

    @Test
    public void shouldCreateNewTrade() {
        //Given
        TradeDto createRequest = new TradeDto();
        createRequest.setSymbol("AAL.L");
        createRequest.setRate(new BigDecimal("100"));
        createRequest.setAmount(100L);

        TradeDto responseDto = new TradeDto();
        responseDto.setSymbol("AAL.L");
        responseDto.setRate(new BigDecimal("100"));
        responseDto.setAmount(100L);
        when(tradeService.createNewTrade(createRequest)).thenReturn(responseDto);

        //When
        TradeDto actual = tradeController.create(createRequest);

        //Then
        assertThat(actual.getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.getAmount()).isEqualTo(100L);
        assertThat(actual.getRate()).isEqualTo(new BigDecimal("100"));
    }
}