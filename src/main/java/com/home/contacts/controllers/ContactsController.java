package com.home.contacts.controllers;

import com.home.contacts.dto.ContactCreationDto;
import com.home.contacts.entity.ContactEntity;
import com.home.contacts.exceptions.ResourceNotFoundException;
import com.home.contacts.mapper.ContactMapper;
import com.home.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/auth")
public class ContactsController {

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("contacts",
                contactService.findAllByOwnerUsername(principal.getName()));
        model.addAttribute("username", principal.getName());
        return "index";
    }

    @GetMapping(value = "/new")
    public String showAddContactForm(@ModelAttribute("contact") ContactCreationDto contact) {
        return "add";
    }

    @PostMapping(value = "/new")
    public String addContact(@ModelAttribute("contact") @Valid ContactCreationDto contact,
                             BindingResult result,
                             Principal principal) {
        ContactEntity existingContact = contactService.findByPhoneNumberAndOwnerUsername(
                contact.getPhoneNumber(),
                principal.getName()
        );

        if (existingContact != null) {
            result.rejectValue("phoneNumber", null, "There is already a contact with that phone");
        }

        if (result.hasErrors()) {
            return "add";
        }

        contactService.save(contact, principal.getName());
        return "redirect:new?success";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("contact",
                contactMapper.toDto(contactService.findById(id)));
        return "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String update(@ModelAttribute("contact") @Valid ContactCreationDto contact,
                         BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "edit";
        }

        contactService.update(id, contact);
        return "redirect:{id}?success";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        contactService.delete(id);
        return "redirect:auth";
    }
}


