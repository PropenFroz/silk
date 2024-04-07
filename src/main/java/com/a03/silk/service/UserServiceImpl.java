package com.a03.silk.service;

import com.a03.silk.dto.request.CreateUserRequestDTO;
import com.a03.silk.dto.request.LoginJwtRequestDTO;
import com.a03.silk.model.UserModel;
import com.a03.silk.repository.UserDb;
import com.a03.silk.restservice.UserRestService;
import com.a03.silk.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserModel addUser(UserModel user, CreateUserRequestDTO createUserRequestDTO) {
        user.setRole(roleService.getRoleByRoleName(createUserRequestDTO.getRole()));
        String hashedPass = userRestService.encryptPassword(user.getPassword());
        user.setPassword(hashedPass);
        return userDb.save(user);
    }
}
