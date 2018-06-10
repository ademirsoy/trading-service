package com.alidemirsoy.tradingservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("api.security")
public class SecurityProperties {

    private String username;

    private String password;
}
