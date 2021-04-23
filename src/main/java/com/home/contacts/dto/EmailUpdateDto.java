package com.home.contacts.dto;

import com.home.contacts.constraint.FieldMatch;
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
                firstField = "newEmail",
                secondField = "confirmEmail",
                message = "Emails mismatch!"
        )
})
public class EmailUpdateDto {

    @Email
    @NotEmpty(message = "Enter the old email")
    private String oldEmail;

    @Email
    @NotEmpty(message = "Email field should be not empty")
    private String newEmail;

    @Email
    @NotEmpty(message = "Confirm new email!")
    private String confirmEmail;
}
