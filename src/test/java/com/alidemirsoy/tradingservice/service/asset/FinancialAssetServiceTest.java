package com.alidemirsoy.tradingservice.service.asset;


import com.alidemirsoy.tradingservice.domain.FinancialAsset;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetCreateRequestDto;
import com.alidemirsoy.tradingservice.dto.asset.FinancialAssetResponseDto;
import com.alidemirsoy.tradingservice.dto.asset.SpreadUpdateRequestDto;
import com.alidemirsoy.tradingservice.exception.BadRequestException;
import com.alidemirsoy.tradingservice.repository.FinancialAssetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FinancialAssetServiceTest {

    @InjectMocks
    FinancialAssetService financialAssetService;

    @Mock
    FinancialAssetRepository financialAssetRepository;

    @Mock
    FinancialAssetResponseConverter financialAssetResponseConverter;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void shouldRetrieveAllAssets() {
        //Given
        FinancialAsset asset1 = new FinancialAsset();
        asset1.setSymbol("AAL.L");
        FinancialAsset asset2 = new FinancialAsset();
        asset2.setSymbol("BP.L");
        Iterable<FinancialAsset> assets = Arrays.asList(asset1, asset2);
        FinancialAssetResponseDto response1 = new FinancialAssetResponseDto();
        response1.setSymbol("AAL.L");
        FinancialAssetResponseDto response2 = new FinancialAssetResponseDto();
        response2.setSymbol("BP.L");

        when(financialAssetRepository.findAll()).thenReturn(assets);
        when(financialAssetResponseConverter.convert(asset1)).thenReturn(response1);
        when(financialAssetResponseConverter.convert(asset2)).thenReturn(response2);

        //When
        List<FinancialAssetResponseDto> actual = financialAssetService.getAllAssets();

        //Then
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.get(1).getSymbol()).isEqualTo("BP.L");
    }

    @Test
    public void shouldCreateNewAsset() {
        //Given
        FinancialAssetCreateRequestDto createRequestDto = new FinancialAssetCreateRequestDto();
        createRequestDto.setSymbol("AAL.L");
        createRequestDto.setRate(new BigDecimal("10"));
        FinancialAsset asset = new FinancialAsset();
        asset.setSymbol("AAL.L");
        asset.setRate(new BigDecimal("10"));
        FinancialAssetResponseDto response = new FinancialAssetResponseDto();
        response.setSymbol("AAL.L");
        response.setRate(new BigDecimal("10"));


        when(modelMapper.map(createRequestDto, FinancialAsset.class)).thenReturn(asset);
        when(financialAssetRepository.save(asset)).thenReturn(asset);
        when(financialAssetResponseConverter.convert(asset)).thenReturn(response);

        //When
        FinancialAssetResponseDto actual = financialAssetService.createNewAsset(createRequestDto);

        //Then
        assertThat(actual.getSymbol()).isEqualTo("AAL.L");
        assertThat(actual.getRate().doubleValue()).isEqualTo(10D);
    }

    @Test
    public void shouldUpdateSpreadWhenRequestIsValid() {
        //Given
        SpreadUpdateRequestDto updateRequest = new SpreadUpdateRequestDto();
        updateRequest.setId(5L);
        updateRequest.setSpread(1.4);

        FinancialAsset asset = new FinancialAsset();
        asset.setId(5L);
        asset.setSpread(0.5);
        asset.setRate(new BigDecimal("100"));
        Optional<FinancialAsset> optional = Optional.of(asset);
        when(financialAssetRepository.findById(5L)).thenReturn(optional);

        ArgumentCaptor<FinancialAsset> argumentCaptor = ArgumentCaptor.forClass(FinancialAsset.class);
        asset.setSpread(1.4);
        FinancialAssetResponseDto responseDto = new FinancialAssetResponseDto();
        responseDto.setSpread(1.4);
        when(financialAssetRepository.save(argumentCaptor.capture())).thenReturn(asset);
        when(financialAssetResponseConverter.convert(asset)).thenReturn(responseDto);

        //When
        FinancialAssetResponseDto actual = financialAssetService.updateSpread(5L, updateRequest);

        //Then
        assertThat(argumentCaptor.getValue().getSpread()).isEqualTo(1.4);
        assertThat(actual.getSpread()).isEqualTo(1.4);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowExceptionWhenAssetNotFound() {
        //Given
        when(financialAssetRepository.findById(5L)).thenReturn(Optional.empty());

        //When
        FinancialAssetResponseDto actual = financialAssetService.updateSpread(5L, new SpreadUpdateRequestDto());
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowExceptionWhenSpreadLargerThanRate() {
        //Given
        SpreadUpdateRequestDto updateRequest = new SpreadUpdateRequestDto();
        updateRequest.setId(5L);
        updateRequest.setSpread(150D);

        FinancialAsset asset = new FinancialAsset();
        asset.setId(5L);
        asset.setSpread(0.5);
        asset.setRate(new BigDecimal("100"));
        Optional<FinancialAsset> optional = Optional.of(asset);
        when(financialAssetRepository.findById(5L)).thenReturn(optional);

        //When
        FinancialAssetResponseDto actual = financialAssetService.updateSpread(5L, updateRequest);
    }
}