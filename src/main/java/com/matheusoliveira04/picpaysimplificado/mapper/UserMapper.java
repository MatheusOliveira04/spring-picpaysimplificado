package com.matheusoliveira04.picpaysimplificado.mapper;

import com.matheusoliveira04.picpaysimplificado.dto.request.UserRequest;
import com.matheusoliveira04.picpaysimplificado.dto.response.UserResponse;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type", qualifiedByName = "mapUserType")
    @Mapping(target = "wallet.balance", source = "walletBalance")
    User toModel(UserRequest request);

    @Named("toResponse")
    @Mapping(target = "type", source = "type", qualifiedByName = "mapDescriptionType")
    @Mapping(target = "walletBalance", source = "wallet.balance")
    UserResponse toResponse(User user);

    @IterableMapping(qualifiedByName = "toResponse")
    @Mapping(target = "type", source = "type", qualifiedByName = "mapDescriptionType")
    List<UserResponse> toResponse(List<User> users);

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
