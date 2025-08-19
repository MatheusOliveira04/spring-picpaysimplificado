package com.matheusoliveira04.picpaysimplificado.mapper;

import com.matheusoliveira04.picpaysimplificado.dto.request.UserRequest;
import com.matheusoliveira04.picpaysimplificado.dto.response.UserResponse;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type", qualifiedByName = "mapUserType")
    User toModel(UserRequest request);

    @Mapping(target = "type", source = "type", qualifiedByName = "mapDescriptionType")
    UserResponse toResponse(User user);

    @Named("mapUserType")
    default UserType mapUserType(String type) {
        return Optional.ofNullable(type)
                .map(UserType::fromDescription)
                .orElseThrow(() -> new IllegalArgumentException("User type invalid: " + type));
    }

    @Named("mapDescriptionType")
    default String mapDescriptionType(UserType type) {
        return Optional.ofNullable(type)
                .map(UserType::getDescription)
                .orElseThrow(() -> new IllegalArgumentException("User type cannot be null"));
    }



}
