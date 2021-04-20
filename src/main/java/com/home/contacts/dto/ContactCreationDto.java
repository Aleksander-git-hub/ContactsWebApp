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
public class ContactCreationDto {

    @NotEmpty(message = "FirstName field should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "LastName field should be not empty")
    @Size(min = 2, max = 30, message = "LastName should be between 2 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Phone field should be not empty")
    @Size(min = 2, max = 20, message = "Phone should be between 2 and 20 characters")
    private String phoneNumber;

    @Email
    @NotEmpty(message = "Email field should be not empty")
    private String email;
}
