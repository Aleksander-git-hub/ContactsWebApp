package com.home.contacts.service;

import com.home.contacts.dto.ContactCreationDto;
import com.home.contacts.entity.ContactEntity;

import java.util.List;

public interface ContactService {

    List<ContactEntity> findAllByOwnerUsername(String username);

    ContactEntity findByPhoneNumberAndOwnerUsername(String phone, String username);

    void save(ContactCreationDto newContact, String username);

    ContactEntity findById(Long contactId);

    void update(Long contactId, ContactCreationDto contact);
}
