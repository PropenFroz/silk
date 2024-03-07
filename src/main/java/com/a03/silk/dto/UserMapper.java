package com.a03.silk.dto;

import com.a03.silk.dto.request.CreateUserRequestDTO;
import com.a03.silk.dto.request.UpdateUserRequestDTO;
import com.a03.silk.dto.response.CreateUserResponseDTO;
import com.a03.silk.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    UserModel createUserRequestDTOToUserModel(CreateUserRequestDTO createUserRequestDTO);

    @Mapping(target = "role", ignore = true)
    CreateUserResponseDTO createUserResponseDTOToUserModel(UserModel userModel);
    UserModel updateUserModelRequestDTOToUserModel(UpdateUserRequestDTO updateUserRequestDTO);
}
