package com.home.contacts.dto;

import com.home.contacts.constraint.FieldMatch;
import com.home.contacts.constraint.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldMatch.List({
        @FieldMatch(
                firstField = "newPassword",
                secondField = "confirmPassword",
                message = "Passwords do not match!"
        ),
        @FieldMatch(
                firstField = "email",
                secondField = "confirmEmail",
                message = "Email do not match!"
        )
})
public class PasswordUpdateDto {

    @NotEmpty(message = "Old Password field should be not empty")
    private String oldPassword;

    @ValidPassword
    @NotEmpty(message = "New Password field should be not empty")
    private String newPassword;

    @ValidPassword
    @NotEmpty(message = "Confirm your new password")
    private String confirmPassword;

    @Email
    @NotEmpty(message = "Email field should be not empty")
    private String email;

    @Email
    @NotEmpty(message = "Confirm your email!")
    private String confirmEmail;
}
