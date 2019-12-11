package restclient.service;

import restclient.domain.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();
    Role getSingleRole(int id);
    Role getSingleRoleByName(String role);
}
