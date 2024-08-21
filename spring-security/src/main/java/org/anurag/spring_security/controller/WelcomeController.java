package org.anurag.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello all!";
    }

    @GetMapping("/user")
    public String welcomeUser() {
        return "Hello user!";
    }

    @GetMapping("/admin")
    public String welcomeAdmin() {
        return "Hello Admin!";
    }
}
