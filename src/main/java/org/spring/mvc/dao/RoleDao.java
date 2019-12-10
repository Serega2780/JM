package org.spring.mvc.dao;

import org.spring.mvc.domain.Role;
import org.spring.mvc.domain.User;
import org.spring.mvc.service.DBException;

import java.util.List;

public interface RoleDao {

    void insertRole(Role role) throws DBException;

    Role selectRoleById(int id) throws DBException;

    Role selectRoleByName(String name) throws DBException;

    List<Role> selectAllRoles() throws DBException;

    boolean deleteRole(int id) throws DBException;

    void updateRole(Role role) throws DBException;
}
