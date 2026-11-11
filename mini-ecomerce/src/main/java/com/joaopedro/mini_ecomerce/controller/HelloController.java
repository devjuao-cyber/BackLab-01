package com.joaopedro.mini_ecomerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> hello() {

        return Map.of(
                "mensagem",
                "Olá! Minha primeira API Java está funcionando1212321."
        );
    }
}