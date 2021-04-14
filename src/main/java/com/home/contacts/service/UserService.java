package com.home.contacts.service;

import com.home.contacts.dto.UserCreationDto;
import com.home.contacts.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(UserCreationDto userDto);

    UserEntity findByUsername(String username);

    void activate(String code);
}
