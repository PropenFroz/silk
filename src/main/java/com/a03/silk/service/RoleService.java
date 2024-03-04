package com.a03.silk.service;

import com.a03.silk.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllList();

    Role getRoleByRoleName(String name);

}
