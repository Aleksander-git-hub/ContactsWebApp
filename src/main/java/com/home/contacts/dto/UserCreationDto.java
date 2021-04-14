package com.home.contacts.dto;

import com.home.contacts.constraint.FieldMatch;
import com.home.contacts.entity.enums.Gender;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserCreationDto {

    @NotEmpty(message = "Username field should be not empty")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30 characters")
    private String username;

    @NotEmpty(message = "FirstName field should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "LastName field should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Age field should be not empty")
    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;

    @NotEmpty(message = "Phone field should be not empty")
    @Size(min = 2, max = 20, message = "Phone should be between 2 and 20 characters")
    private String phoneNumber;

    @Email
    @NotEmpty(message = "Email field should be not empty")
    private String email;

    @Email
    @NotEmpty(message = "Confirm your email!")
    private String confirmEmail;

    private Gender gender;

    @NotEmpty(message = "Password field should be not empty")
    @Size(min = 6, max = 15, message = "Password should be between 6 and 15 characters")
    private String password;

    @NotEmpty(message = "Confirm your password!")
    private String confirmPassword;
}
