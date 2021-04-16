package com.home.contacts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
