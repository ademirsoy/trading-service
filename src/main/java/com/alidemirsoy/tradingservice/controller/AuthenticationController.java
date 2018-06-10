package com.alidemirsoy.tradingservice.controller;

import com.alidemirsoy.tradingservice.dto.auth.AuthenticationRequestDto;
import com.alidemirsoy.tradingservice.dto.auth.AuthenticationResponseDto;
import com.alidemirsoy.tradingservice.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")     //TODO DELETE THIS
@RestController
@RequestMapping(path = "login")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/auth")
    public AuthenticationResponseDto authenticate(@RequestBody @Validated AuthenticationRequestDto authenticationRequestDto) {
        return authenticationService.authenticateUser(authenticationRequestDto);
    }
}