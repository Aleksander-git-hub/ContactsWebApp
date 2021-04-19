package com.home.contacts.controllers;

import com.home.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String welcome() {
        return "home";
    }

    @GetMapping(value = "/auth")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value = "/403")
    public String error403() {
        return "error/403";
    }

    /*@GetMapping(value = "/banned")
    public String banned() {
        return "error/banned";
    }*/

}
