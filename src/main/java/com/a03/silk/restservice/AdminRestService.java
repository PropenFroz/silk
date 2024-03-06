package com.a03.silk.restservice;

import com.a03.silk.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface AdminRestService {


    String getToken(String username, String name);

    List<UserModel> retrieveRestAllUser();

    UserModel getRestUserById(UUID id);


    UserModel getRestUserModelByUsername(String username);

    void saveRestUserModel(UserModel user);

    UserModel createRestUserModel(UserModel userModel, String userType, CreateUserModelRequestDTO UserModelDTO);

    String encryptPassword(String plainPassword);

    boolean verifyPassword(String plainPassword, String encodedPassword);

    UserModel updateRestUserModel(UserModel UserModelFromDTO, UUID id);

    UserModel deleteUserModel(UserModel UserModel);

    void updateBalance(UserModel UserModel, Long amount, String action);


    String loginSeller(LoginJwtRequestDTO loginJwtRequestDTO);
}
