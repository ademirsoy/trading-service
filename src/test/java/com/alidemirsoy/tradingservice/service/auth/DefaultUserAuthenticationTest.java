package com.alidemirsoy.tradingservice.service.auth;

import com.alidemirsoy.tradingservice.configuration.SecurityProperties;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserAuthenticationTest {

    @InjectMocks
    DefaultUserAuthentication defaultUserAuthentication;

    @Mock
    private SecurityProperties securityProperties;

    @Test
    public void shouldAuthenticateWhenCredentialsValid() {
        //Given
        AuthenticationRequestDto request = new AuthenticationRequestDto();
        request.setUsername("user");
        request.setPassword("pass");

        when(securityProperties.getUsername()).thenReturn("user");
        when(securityProperties.getPassword()).thenReturn("pass");

        //When
        AuthenticationResponseDto actual = defaultUserAuthentication.authenticate(request);

        //Then
        assertThat(actual.getToken()).isNotBlank();
        assertThat(actual.getUsername()).isEqualTo("user");
    }

    @Test(expected = BadCredentialsException.class)
    public void shouldThrowExceptionWhenCredentialsInvalid() {
        //Given
        AuthenticationRequestDto request = new AuthenticationRequestDto();
        request.setUsername("user");
        request.setPassword("pass");

        when(securityProperties.getUsername()).thenReturn("user");
        when(securityProperties.getPassword()).thenReturn("anotherPass");

        //When
        defaultUserAuthentication.authenticate(request);
    }
}