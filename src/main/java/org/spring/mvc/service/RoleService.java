package org.spring.mvc.service;

import org.spring.mvc.domain.Role;

import java.util.List;

public interface RoleService {

    void insertRole(Role role);

    Role selectRoleById(int id);

    Role selectRoleByName(String name);

    List<Role> selectAllRoles();

    boolean deleteRole(int id);

    void updateRole(Role role);
}
