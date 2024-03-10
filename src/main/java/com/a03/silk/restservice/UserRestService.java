package com.a03.silk.restservice;

import com.a03.silk.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserRestService {
    String getToken(String username, String name);

    List<UserModel> retrieveRestAllUser();

    UserModel getRestUserById(Long id);

    UserModel getRestUserByUsername(String username);

    void saveRestUser(UserModel user);

    String encryptPassword(String plainPassword);

    boolean verifyPassword(String plainPassword, String encodedPassword);

    UserModel updateRestUser(UserModel UserFromDTO, Long id);

    UserModel deleteUser(UserModel user);

}
