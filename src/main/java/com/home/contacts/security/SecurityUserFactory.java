package com.home.contacts.security;

import com.home.contacts.entity.RoleEntity;
import com.home.contacts.entity.UserEntity;
import com.home.contacts.entity.enums.Status;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SecurityUserFactory {

    public static SecurityUser securityUserFromUserEntity(UserEntity userEntity) {
        return new SecurityUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                mapToGrantedAuthorities(userEntity.getRoles()),
                userEntity.getStatus().equals(Status.ACTIVE)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
