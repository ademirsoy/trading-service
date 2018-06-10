package com.alidemirsoy.tradingservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "FINANCIAL_ASSET")
public class FinancialAsset extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "asset_gen")
    @SequenceGenerator(name = "asset_gen", sequenceName = "ASSET_SEQ")
    private Long id;

    private String symbol;

    private BigDecimal rate;

    private Double spread;

}
