package com.alidemirsoy.tradingservice.service.asset;

import com.alidemirsoy.tradingservice.domain.FinancialAsset;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinancialAssetResponseConverter {

    public FinancialAssetResponseDto convert(FinancialAsset financialAsset) {
        FinancialAssetResponseDto responseDto = new FinancialAssetResponseDto();
        responseDto.setId(financialAsset.getId());
        responseDto.setSymbol(financialAsset.getSymbol());
        responseDto.setRate(financialAsset.getRate());
        responseDto.setSpread(financialAsset.getSpread());
        responseDto.setBid(financialAsset.getRate().subtract(new BigDecimal(financialAsset.getSpread())));
        responseDto.setOffer(financialAsset.getRate().add(new BigDecimal(financialAsset.getSpread())));
        responseDto.setCreateDate(financialAsset.getCreateDate());
        responseDto.setUpdateDate(financialAsset.getUpdateDate());

        return responseDto;
    }
}
