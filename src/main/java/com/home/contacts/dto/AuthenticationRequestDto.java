package com.home.contacts.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class AuthenticationRequestDto {

    @NotEmpty(message = "Username field is empty!")
    private String username;

    @NotEmpty(message = "Password field is empty!")
    private String password;
}
