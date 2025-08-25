package com.yasin.jwtsecutyapi.controller;

import com.yasin.jwtsecutyapi.model.dto.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping
    public String login(@RequestBody LoginDTO loginDTO){
        return null;
    }
}
