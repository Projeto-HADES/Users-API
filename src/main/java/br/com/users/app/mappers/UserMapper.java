package br.com.users.app.mappers;

import br.com.users.app.dto.UserDto;
import br.com.users.app.entity.AdmEntity;
import br.com.users.app.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("uuidToAdmEntity")
    default AdmEntity uuidToAdmEntity(UUID admId) {
        if (admId == null) {
            return null;
        }
        return new AdmEntity(admId);
    }

    @Named("admEntityToUuid")
    default UUID admEntityToUuid(AdmEntity admEntity) {
        if (admEntity == null) {
            return null;
        }
        return admEntity.getId();
    }

    @Mapping(source = "admId", target = "admId", qualifiedByName = "uuidToAdmEntity")
    UserEntity toModel(UserDto userDto);

    @Mapping(source = "admId", target = "admId", qualifiedByName = "admEntityToUuid")
    UserDto toDto(UserEntity userEntity);

    List<UserDto> toDoList(List<UserEntity> users);
}
