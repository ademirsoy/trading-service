package com.alidemirsoy.tradingservice.service.trade;

import com.alidemirsoy.tradingservice.domain.Trade;
import com.alidemirsoy.tradingservice.dto.trade.TradeDto;
import com.alidemirsoy.tradingservice.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TradeDto> getAllTrades() {
        Iterable<Trade> trades = tradeRepository.findAll();
        return StreamSupport
                .stream(trades.spliterator(), false)
                .map(trade -> modelMapper.map(trade, TradeDto.class))
                .collect(Collectors.toList());
    }

    public TradeDto createNewTrade(TradeDto createRequestDto) {
        Trade trade = modelMapper.map(createRequestDto, Trade.class);
        trade.setCreateDate(LocalDateTime.now());
        Trade savedTrade = tradeRepository.save(trade);
        return modelMapper.map(savedTrade, TradeDto.class);
    }
}
