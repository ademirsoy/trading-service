package com.alidemirsoy.tradingservice.service.asset;

import com.alidemirsoy.tradingservice.domain.FinancialAsset;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetCreateRequestDto;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetResponseDto;
import com.alidemirsoy.tradingservice.dto.asset.SpreadUpdateRequestDto;
import com.alidemirsoy.tradingservice.exception.BadRequestException;
import com.alidemirsoy.tradingservice.repository.FinancialAssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class FinancialAssetService {

    @Autowired
    FinancialAssetRepository financialAssetRepository;

    @Autowired
    FinancialAssetResponseConverter financialAssetResponseConverter;

    @Autowired
    private ModelMapper modelMapper;

    public List<FinancialAssetResponseDto> getAllAssets() {
        Iterable<FinancialAsset> assets = financialAssetRepository.findAll();
        return StreamSupport
                .stream(assets.spliterator(), false)
                .map(asset -> financialAssetResponseConverter.convert(asset))
                .collect(Collectors.toList());
    }

    public FinancialAssetResponseDto createNewAsset(FinancialAssetCreateRequestDto createRequestDto) {
        FinancialAsset financialAsset = modelMapper.map(createRequestDto, FinancialAsset.class);
        financialAsset.setCreateDate(LocalDateTime.now());
        FinancialAsset savedFinancialAsset = financialAssetRepository.save(financialAsset);
        return financialAssetResponseConverter.convert(savedFinancialAsset);
    }

    public FinancialAssetResponseDto updateSpread(Long id, SpreadUpdateRequestDto updateRequestDto) {
        Optional<FinancialAsset> optional = financialAssetRepository.findById(id);
        FinancialAsset existingAsset = optional.orElseThrow(() ->
                new BadRequestException(String.format("Asset with id: %s does not exist!", id), "error.asset.notFound"));

        validateSpread(updateRequestDto.getSpread(), existingAsset.getRate().doubleValue());

        existingAsset.setSpread(updateRequestDto.getSpread());
        existingAsset.setUpdateDate(LocalDateTime.now());
        FinancialAsset savedFinancialAsset = financialAssetRepository.save(existingAsset);
        return financialAssetResponseConverter.convert(savedFinancialAsset);
    }

    private void validateSpread(Double spread, Double rate) {
        if (spread >= rate) {
            throw new BadRequestException("Spread value cannot be larger than or equal to the rate value", "error.spread.invalid");
        }
    }
}
