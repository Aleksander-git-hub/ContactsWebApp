package com.home.contacts.service;

import com.home.contacts.dto.UserCreationDto;
import com.home.contacts.entity.UserEntity;

public interface UserService {

    void save(UserCreationDto userDto);

    UserEntity findByUsername(String username);

    void activate(String code);

    UserEntity findById(Long id);
}
