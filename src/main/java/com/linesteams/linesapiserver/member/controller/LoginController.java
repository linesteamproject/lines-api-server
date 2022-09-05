package com.linesteams.linesapiserver.member.controller;

import com.linesteams.linesapiserver.member.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private final JwtService jwtService;

    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public String test(Model model) {
        return "index";
    }
}
