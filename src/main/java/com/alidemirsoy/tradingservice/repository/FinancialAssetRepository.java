package com.alidemirsoy.tradingservice.repository;


import com.alidemirsoy.tradingservice.domain.FinancialAsset;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;

public interface FinancialAssetRepository extends CrudRepository<FinancialAsset, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO financial_asset (symbol, rate, spread) VALUES (:symbol, :rate, :spread)", nativeQuery = true)
    void insert(@Param("symbol") String symbol, @Param("rate") BigDecimal rate, @Param("spread") Double spread);
}
