package com.home.contacts.mapper;

import com.home.contacts.dto.EmailUpdateDto;
import com.home.contacts.dto.UserUpdateDto;
import com.home.contacts.dto.PasswordUpdateDto;
import com.home.contacts.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserUpdateDto toUpdateDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    void updateUser(UserUpdateDto source, @MappingTarget UserEntity target);

    PasswordUpdateDto toPasswordUpdateDto(UserEntity byUsername);

    EmailUpdateDto toEmailUpdateDto(UserEntity byUsername);
}
