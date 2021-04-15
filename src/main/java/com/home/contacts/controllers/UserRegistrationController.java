package com.home.contacts.controllers;

import com.home.contacts.dto.UserCreationDto;
import com.home.contacts.entity.UserEntity;
import com.home.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserCreationDto userCreationDto() {
        return new UserCreationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @Valid UserCreationDto userDto,
                               BindingResult result) {
        UserEntity existingUser = userService.findByUsername(userDto.getUsername());

        if (existingUser != null) {
            result.rejectValue("username", null, "There is already an account with that email");
            // TODO check it
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping(value = "/activate/{code}")
    public String activate(@PathVariable String code) {
        userService.activate(code);
        return "confirm";
    }
}
