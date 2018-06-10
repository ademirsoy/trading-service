package com.alidemirsoy.tradingservice.service.auth;

import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    private UserAuthentication userAuthentication;

    @Test
    public void shouldAuthenticateUser() {
        //Given
        AuthenticationRequestDto request = new AuthenticationRequestDto();
        request.setUsername("user");
        request.setPassword("pass");

        AuthenticationResponseDto response = new AuthenticationResponseDto();
        response.setUsername("user");
        response.setToken("1234qwerty");
        when(userAuthentication.authenticate(request)).thenReturn(response);

        //When
        AuthenticationResponseDto actual = authenticationService.authenticateUser(request);

        //Then
        assertThat(actual.getToken()).isEqualTo("1234qwerty");
        assertThat(actual.getUsername()).isEqualTo("user");

    }
}