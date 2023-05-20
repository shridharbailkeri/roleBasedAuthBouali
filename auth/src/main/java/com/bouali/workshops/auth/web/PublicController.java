package com.bouali.workshops.auth.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping("/")
    public String publicEndpoint() {
        return "<h1>Hello from public endpoint</h1>";
    }
}
