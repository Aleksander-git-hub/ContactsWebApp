package com.home.contacts.service.impl;

import com.home.contacts.dto.ContactCreationDto;
import com.home.contacts.entity.ContactEntity;
import com.home.contacts.entity.enums.Status;
import com.home.contacts.repository.ContactRepository;
import com.home.contacts.repository.UserRepository;
import com.home.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ContactEntity> findAllByOwnerUsername(String username) {
        return contactRepository.findAllByOwnerUsername(username);
    }

    @Override
    public ContactEntity findByPhoneNumberAndOwnerUsername(String phone, String username) {
        return contactRepository.findByPhoneNumberAndOwnerUsername(phone, username);
    }

    @Override
    public void save(ContactCreationDto newContact, String username) {
        ContactEntity contact = ContactEntity.builder()
                .firstName(newContact.getFirstName())
                .lastName(newContact.getLastName())
                .phoneNumber(newContact.getPhoneNumber())
                .email(newContact.getEmail())
                .created(new Date())
                .updated(new Date())
                .status(Status.ACTIVE)
                .owner(userRepository.findByUsername(username))
                .build();

        contactRepository.save(contact);
    }

}
