package com.home.contacts.repository;

import com.home.contacts.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> findAllByOwnerUsername(String username);

    ContactEntity findByPhoneNumberAndOwnerUsername(String phone, String owner);

}
