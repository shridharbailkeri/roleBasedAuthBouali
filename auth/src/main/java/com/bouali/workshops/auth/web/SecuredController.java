package com.bouali.workshops.auth.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secured")
public class SecuredController {
    @GetMapping("/")
    public String securedEndpoint() {
        return "<h1>Hello from secured endpoint</h1>";
    }
    //@EnableGlobalMethodSecurity(prePostEnabled = true)
    // prePostEnabled = true means before executing /admin endpoint, i want to check this @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "<h1>Hello from admin endpoint</h1>";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "<h1>Hello from user endpoint</h1>";
    }

    @GetMapping("/admin-user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String adminUserEndpoint() {
        return "<h1>Hello from admin-user endpoint</h1>";
    }
}
