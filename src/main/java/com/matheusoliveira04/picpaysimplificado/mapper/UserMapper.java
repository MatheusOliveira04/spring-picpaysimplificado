package com.matheusoliveira04.picpaysimplificado.mapper;

import com.matheusoliveira04.picpaysimplificado.dto.request.UserRequest;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type", qualifiedByName = "mapUserType")
    User toModel(UserRequest request);

    @Named("mapUserType")
    default UserType mapUserType(String type) {
        return UserType.fromDescription(type);
    }


}
