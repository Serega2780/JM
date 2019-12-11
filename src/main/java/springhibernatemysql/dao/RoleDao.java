package springhibernatemysql.dao;


import springhibernatemysql.domain.Role;

import java.util.Set;

public interface RoleDao {
    Role getRole(int id);

    Role getRoleByName(String role);

    Set<Role> getRoles();

}
