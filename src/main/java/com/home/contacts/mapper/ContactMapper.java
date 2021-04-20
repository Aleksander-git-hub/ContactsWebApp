package com.home.contacts.mapper;

import com.home.contacts.dto.ContactCreationDto;
import com.home.contacts.entity.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    void updateContact(ContactCreationDto source, @MappingTarget ContactEntity target);

    ContactCreationDto toDto(ContactEntity contactEntity);
}
