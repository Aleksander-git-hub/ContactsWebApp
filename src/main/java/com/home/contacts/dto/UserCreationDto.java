package com.home.contacts.dto;

import com.home.contacts.constraint.FieldMatch;
import com.home.contacts.constraint.ValidPassword;
import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldMatch.List({
        @FieldMatch(
                firstField = "password",
                secondField = "confirmPassword",
                message = "Passwords do not match!"
        ),
        @FieldMatch(
                firstField = "email",
                secondField = "confirmEmail",
                message = "Email do not match!"
        )
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

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 100, message = "You are so old! Please, let's tell the no truth about age =)")
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

    private String gender;

    @ValidPassword
    @NotEmpty(message = "Password field should be not empty")
    private String password;

    @ValidPassword
    @NotEmpty(message = "Confirm your password")
    private String confirmPassword;
}
