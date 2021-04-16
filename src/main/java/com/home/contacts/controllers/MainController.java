package com.home.contacts.controllers;

import com.home.contacts.dto.AuthenticationRequestDto;
import com.home.contacts.entity.UserEntity;
import com.home.contacts.entity.enums.Status;
import com.home.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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
    public String login(Model model) {
        model.addAttribute("user", new AuthenticationRequestDto());
        return "login";
    }

    @PostMapping(value = "/login")
    public String authenticationUser(@RequestBody @Valid AuthenticationRequestDto requestDto,
                                     BindingResult result) {
        UserEntity user = userService.findByUsername(requestDto.getUsername());

        if (!user.getStatus().equals(Status.ACTIVE)) {
            result.rejectValue("username", null, "Your status is not 'Active'!");
        }

        if (result.hasErrors()) {
            return "login";
        }

        return "index";
    }

    @GetMapping(value = "/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping(value = "/banned")
    public String banned() {
        return "error/banned";
    }

}
