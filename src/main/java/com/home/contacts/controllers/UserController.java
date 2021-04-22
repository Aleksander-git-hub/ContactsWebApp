package com.home.contacts.controllers;

import com.home.contacts.dto.PasswordUpdateDto;
import com.home.contacts.dto.UserUpdateDto;
import com.home.contacts.entity.UserEntity;
import com.home.contacts.mapper.UserMapper;
import com.home.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user-update")
    public String showUpdateFormForUser(Model model, Principal principal) {
        model.addAttribute("user",
                userMapper.toUpdateDto(userService.findByUsername(principal.getName())));
        return "user_update";
    }

    @PostMapping(value = "/user-update")
    public String updateUser(@ModelAttribute("user") @Valid UserUpdateDto user,
                             BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "user_update";
        }

        userService.update(user, userService.findByUsername(principal.getName()).getId());
        return "redirect:user-update?success";
    }

    @GetMapping(value = "/user-update-password")
    public String showPasswordUpdateForm(Model model, Principal principal) {
        model.addAttribute("user",
                userMapper.toPasswordUpdateDto(userService.findByUsername(principal.getName())));
        return "password";
    }

    @PostMapping(value = "/user-update-password")
    public String updateUsername(@ModelAttribute("user") @Valid PasswordUpdateDto passwordDto,
                                 BindingResult result, Principal principal) {
        UserEntity existingUser = userService.findByUsername(principal.getName());

        if (!passwordEncoder.matches(passwordDto.getOldPassword(), existingUser.getPassword())) {
            result.rejectValue("oldPassword", null, "Old Password mismatch");
        }

        if (!passwordDto.getEmail().equals(existingUser.getEmail())) {
            result.rejectValue("email", null, "Incorrect email!");
        }

        if (result.hasErrors()) {
            return "password";
        }

        userService.updatePassword(passwordDto, existingUser);
        return "confirm_updated";
    }
}
