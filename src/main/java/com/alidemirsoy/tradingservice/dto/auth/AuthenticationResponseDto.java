package com.alidemirsoy.tradingservice.dto.auth;

import lombok.Data;

@Data
public class AuthenticationResponseDto {

    private String username;

    private String token;
}
