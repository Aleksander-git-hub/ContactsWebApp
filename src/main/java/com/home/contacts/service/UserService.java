package com.home.contacts.service;

import com.home.contacts.dto.EmailUpdateDto;
import com.home.contacts.dto.PasswordUpdateDto;
import com.home.contacts.dto.UserCreationDto;
import com.home.contacts.dto.UserUpdateDto;
import com.home.contacts.entity.UserEntity;

public interface UserService {

    void save(UserCreationDto userDto);

    UserEntity findByUsername(String username);

    void activate(String code);

    UserEntity findById(Long id);

    void update(UserUpdateDto user, Long id);

    void updatePassword(PasswordUpdateDto passwordDto, UserEntity user);

    void updateEmail(EmailUpdateDto emailDto, UserEntity user);
}
