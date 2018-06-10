package com.alidemirsoy.tradingservice.controller;

import com.alidemirsoy.tradingservice.dto.trade.TradeDto;
import com.alidemirsoy.tradingservice.service.trade.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")     //TODO DELETE THIS
@RestController
@RequestMapping(path = "trades")
public class TradeController {

    @Autowired
    TradeService tradeService;

    @GetMapping
    public Iterable<TradeDto> getAll() {
        List<TradeDto> trades = tradeService.getAllTrades();
        log.info("Retrieved #{} trades", trades.size());
        return trades;
    }

    @PostMapping
    public TradeDto create(@RequestBody @Validated TradeDto createRequestDto) {
        TradeDto trade = tradeService.createNewTrade(createRequestDto);
        log.info("New trade({}) is saved for symbol: {}", trade.getType(), trade.getSymbol());
        return trade;
    }
}
