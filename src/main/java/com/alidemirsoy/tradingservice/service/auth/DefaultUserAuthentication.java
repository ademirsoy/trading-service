package com.alidemirsoy.tradingservice.service.auth;

import com.alidemirsoy.tradingservice.configuration.SecurityProperties;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationResponseDto;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


@Data
@Service
public class DefaultUserAuthentication implements UserAuthentication {

    private SecurityProperties securityProperties;

    public DefaultUserAuthentication(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * This method is for authenticating the default user credentials.
     * Default user is determined in the configuration file.
     * @param requestDto This has the username and password
     * @return responseDto This has the Basic Authentication header content for the clients to use in further requests.
     */
    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        if (username.equals(securityProperties.getUsername())
                && password.equals(securityProperties.getPassword())) {
            AuthenticationResponseDto responseDto = new AuthenticationResponseDto();
            responseDto.setUsername(username);
            //Return the whole Authorization header content
            // so that when the implementation changes from Basic Auth to OAuth2, clients will not be effected
            responseDto.setToken("Basic " + Base64.encodeBase64String(String.format("%s:%s", username, password).getBytes()));
            return responseDto;
        }
        throw new BadCredentialsException("Invalid credentials, username or password is not correct!");
    }
}
