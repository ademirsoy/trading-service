package com.alidemirsoy.tradingservice.service.auth;

import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationResponseDto;

public class AuthenticationService {

    private UserAuthentication userAuthentication;

    public AuthenticationService(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public AuthenticationResponseDto authenticateUser(AuthenticationRequestDto requestDto) {
        return this.userAuthentication.authenticate(requestDto);
    }
}
