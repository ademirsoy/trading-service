package com.alidemirsoy.tradingservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Data
@Configuration
@EnableWebSecurity
public class TradingServiceSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/*").permitAll()
                .anyRequest().fullyAuthenticated()
                .and().httpBasic();
    }

    /**
     * Basic Authentication is used for a default single user.
     * Default username and password is taken from the configuration.
     * Role based authorization is not implemented for the scope of this application
     * @param auth
     * @throws Exception This is thrown when an unexpected error occurs during setup
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(securityProperties.getUsername())
                .password("{noop}" + securityProperties.getPassword())
                .roles("USER");
    }
}
