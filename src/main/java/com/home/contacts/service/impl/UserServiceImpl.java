package com.home.contacts.service.impl;

import com.home.contacts.dto.UserCreationDto;
import com.home.contacts.email.MailSender;
import com.home.contacts.entity.RoleEntity;
import com.home.contacts.entity.UserEntity;
import com.home.contacts.entity.enums.Status;
import com.home.contacts.repository.RoleRepository;
import com.home.contacts.repository.UserRepository;
import com.home.contacts.service.MessageGenerate;
import com.home.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserCreationDto userDto) {
        RoleEntity role = roleRepository.findByName("ROLE_USER");
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .created(new Date())
                .updated(new Date())
                .status(Status.NOT_CONFIRMED)
                .roles(new ArrayList<>())
                .contacts(new ArrayList<>())
                .activationCode(UUID.randomUUID().toString())
                .build();
        user.getRoles().add(role);

        String message = MessageGenerate.getMessageForUser(user);
        mailSender.send(user.getEmail(), "Activation code", message);

        userRepository.save(user);
    }


    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void activate(String code) {
        UserEntity user = userRepository.findByActivationCode(code);
        user.setActivationCode(null);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
