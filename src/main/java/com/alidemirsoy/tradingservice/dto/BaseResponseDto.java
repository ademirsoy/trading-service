package com.alidemirsoy.tradingservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseResponseDto {

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
