package com.home.contacts.service;

import com.home.contacts.dto.*;
import com.home.contacts.entity.UserEntity;

public interface UserService {

    void save(UserCreationDto userDto);

    UserEntity findByUsername(String username);

    void activate(String code);

    UserEntity findById(Long id);

    void update(UserUpdateDto user, Long id);

    void updatePassword(PasswordUpdateDto passwordDto, UserEntity user);

    void updateEmail(EmailUpdateDto emailDto, UserEntity user);

    void updateUsername(UsernameDto usernameDto, UserEntity user);
}
