package com.alidemirsoy.tradingservice.service.auth;

import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationResponseDto;

public interface UserAuthentication {

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
