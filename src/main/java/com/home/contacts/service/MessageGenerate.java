package com.home.contacts.service;

import com.home.contacts.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageGenerate {

    private static String hostname;

    @Value("${hostname}")
    public void setHostname(String hostname) {
        MessageGenerate.hostname = hostname;
    }

    public static String getMessageForUser(UserEntity user) {
        return String.format(
                "Hello, %s!\n" +
                        "Welcome to ContactsApp! Please, visit next link:\n " +
                        "http://%s/registration/activate/%s",
                user.getUsername(),
                hostname,
                user.getActivationCode()
        );
    }
}
