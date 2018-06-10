package com.alidemirsoy.tradingservice.configuration;

import com.alidemirsoy.tradingservice.service.auth.AuthenticationService;
import com.alidemirsoy.tradingservice.service.auth.DefaultUserAuthentication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradingServiceConfiguration {

    @Autowired
    SecurityProperties securityProperties;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuthenticationService authenticationService() {
        return new AuthenticationService(new DefaultUserAuthentication(this.securityProperties));
    }
}
