package com.a03.silk.service;
import com.a03.silk.dto.request.CreateUserRequestDTO;
import com.a03.silk.dto.request.LoginJwtRequestDTO;
import com.a03.silk.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user, CreateUserRequestDTO createUserRequestDTO);

    String loginJwtAdmin(LoginJwtRequestDTO loginJwtRequestDTO);

}
