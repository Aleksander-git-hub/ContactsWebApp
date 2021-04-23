package com.home.contacts.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UsernameDto {

    @NotEmpty(message = "Enter your new username")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30 characters")
    private String newUsername;

    @Email
    @NotEmpty(message = "Email field should be not empty")
    private String email;
}
