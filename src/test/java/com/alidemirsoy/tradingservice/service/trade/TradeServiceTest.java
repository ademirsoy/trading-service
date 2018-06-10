package com.alidemirsoy.tradingservice.service.trade;

import com.alidemirsoy.tradingservice.domain.Trade;
import com.alidemirsoy.tradingservice.domain.TradeType;
import com.alidemirsoy.tradingservice.dto.trade.TradeDto;
import com.alidemirsoy.tradingservice.repository.TradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

    @InjectMocks
    TradeService tradeService;

    @Mock
    TradeRepository tradeRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void shouldGetAllTrades() {
        //Given
        Trade trade1 = new Trade();
        trade1.setSymbol("AAL.L");
        trade1.setAmount(10L);
        trade1.setType(TradeType.BUY);
        Trade trade2 = new Trade();
        trade2.setSymbol("BP.L");
        trade2.setAmount(20L);
        trade2.setType(TradeType.SELL);
        TradeDto dto1 = new TradeDto(1L, "AAL.L", new BigDecimal("100"), 10L, TradeType.BUY);
        TradeDto dto2 = new TradeDto(2L, "BP.L", new BigDecimal("200"), 20L, TradeType.SELL);

        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade1, trade2));
        when(modelMapper.map(trade1, TradeDto.class)).thenReturn(dto1);
        when(modelMapper.map(trade2, TradeDto.class)).thenReturn(dto2);

        //When
        List<TradeDto> actual = tradeService.getAllTrades();

        //Then
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.get(0).getAmount()).isEqualTo(10L);
        assertThat(actual.get(1).getSymbol()).isEqualTo("BP.L");
        assertThat(actual.get(1).getAmount()).isEqualTo(20L);
    }

    @Test
    public void shouldCreateNewTrade() {
        //Given
        Trade trade = new Trade();
        trade.setSymbol("AAL.L");
        trade.setAmount(10L);
        trade.setType(TradeType.BUY);

        TradeDto createDto = new TradeDto(1L, "AAL.L", new BigDecimal("100"), 10L, TradeType.BUY);
        when(modelMapper.map(createDto, Trade.class)).thenReturn(trade);
        when(tradeRepository.save(trade)).thenReturn(trade);
        when(modelMapper.map(trade, TradeDto.class)).thenReturn(createDto);

        //When
        TradeDto actual = tradeService.createNewTrade(createDto);

        //Then
        assertThat(actual.getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.getAmount()).isEqualTo(10L);
        assertThat(actual.getType()).isEqualTo(TradeType.BUY);
    }
}