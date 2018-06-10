package com.alidemirsoy.tradingservice.repository;


import com.alidemirsoy.tradingservice.domain.Trade;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, Long> {

}
