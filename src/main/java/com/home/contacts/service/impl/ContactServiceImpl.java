package com.home.contacts.service.impl;

import com.home.contacts.dto.ContactCreationDto;
import com.home.contacts.entity.ContactEntity;
import com.home.contacts.entity.enums.Status;
import com.home.contacts.exceptions.ResourceNotFoundException;
import com.home.contacts.mapper.ContactMapper;
import com.home.contacts.repository.ContactRepository;
import com.home.contacts.repository.UserRepository;
import com.home.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactEntity> findAllByOwnerUsername(String username) {
        return contactRepository.findAllByOwnerUsername(username);
    }

    @Override
    public ContactEntity findByPhoneNumberAndOwnerUsername(String phone, String username) {
        return contactRepository.findByPhoneNumberAndOwnerUsername(phone, username);
    }

    @Transactional
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

    @Override
    public ContactEntity findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
    }

    @Transactional
    @Override
    public void update(Long id, ContactCreationDto contact) {
        ContactEntity existingContact = findById(contact.getId());
        contactMapper.updateContact(contact, existingContact);
        existingContact.setUpdated(new Date());
        contactRepository.save(existingContact);
    }

}
