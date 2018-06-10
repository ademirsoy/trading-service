package com.alidemirsoy.tradingservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TRADE")
public class Trade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "trade_gen")
    @SequenceGenerator(name = "trade_gen", sequenceName = "TRADE_SEQ")
    private Long id;

    private String symbol;

    private BigDecimal rate;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private TradeType type;

}
