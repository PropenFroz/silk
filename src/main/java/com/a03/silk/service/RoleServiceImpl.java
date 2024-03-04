package com.a03.silk.service;

import java.util.List;
import java.util.Optional;

import com.a03.silk.model.Role;
import com.a03.silk.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDb roleDb;

    @Override
    public List<Role> getAllList() {
        return roleDb.findAll();
    }

    @Override
    public Role getRoleByRoleName(String name) {
        Optional<Role> role = roleDb.findByRole(name);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }

}
