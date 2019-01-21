package com.telran;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SpringSecurityController {

    @GetMapping("/non-secured")
    public String nonSecured() {
        System.out.println("SpringSecurityController: non-secured");
        return "non-secured works!";
    }

    @GetMapping("/secured")
    public String secured(Principal principal) {

        ;
        System.out.println("Principal name: " + principal.getName());
        System.out.println("SpringSecurityController: secured");
        return "secured works for user " + principal.getName();
    }

}
