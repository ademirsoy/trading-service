package com.alidemirsoy.tradingservice.controller;

import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.service.auth.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    AuthenticationService authenticationService;

    @Test
    public void shouldAuthenticate() {
        //Given
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto();

        //When
        authenticationController.authenticate(requestDto);

        //Then
        verify(authenticationService).authenticateUser(requestDto);
    }
}