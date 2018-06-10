package com.alidemirsoy.tradingservice.controller;

import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetCreateRequestDto;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetResponseDto;
import com.alidemirsoy.tradingservice.dto.asset.SpreadUpdateRequestDto;
import com.alidemirsoy.tradingservice.service.asset.FinancialAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")     //TODO DELETE THIS
@RestController
@RequestMapping(path = "financial-assets")
public class FinancialAssetController {

    @Autowired
    FinancialAssetService financialAssetService;

    @GetMapping
    public Iterable<FinancialAssetResponseDto> getAll() {
        List<FinancialAssetResponseDto> assets = financialAssetService.getAllAssets();
        log.info("Retrieved #{} assets", assets.size());
        return assets;
    }

    @PostMapping
    public FinancialAssetResponseDto create(@RequestBody @Validated FinancialAssetCreateRequestDto createRequestDto) {
        FinancialAssetResponseDto asset = financialAssetService.createNewAsset(createRequestDto);
        log.info("New asset is created with symbol: {}", asset.getSymbol());
        return asset;
    }

    @PatchMapping(path = "/{id}")
    public FinancialAssetResponseDto update(@RequestBody @Validated SpreadUpdateRequestDto updateRequestDto, @PathVariable Long id) {
        FinancialAssetResponseDto asset = financialAssetService.updateSpread(id, updateRequestDto);
        log.info("Spread value updated for symbol: {}", asset.getSymbol());
        return asset;
    }
}
