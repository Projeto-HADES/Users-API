package br.com.users.app.mappers;

import br.com.users.app.dto.AdmDto;
import br.com.users.app.entity.AdmEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdmMapper {
    AdmEntity toModel(AdmDto admDto);
    AdmDto toDto(AdmEntity admEntity);

    List<AdmDto> toDoList(List<AdmEntity> adms);
}
