package springhibernatemysql.service.implementation;

import org.springframework.stereotype.Service;
import springhibernatemysql.dao.RoleDao;
import springhibernatemysql.domain.Role;
import springhibernatemysql.service.RoleService;

import javax.transaction.Transactional;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Set<Role> getAllRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Role getSingleRole(int id) {

        return roleDao.getRole(id);
    }

    @Override
    public Role getSingleRoleByName(String role) {
        return roleDao.getRoleByName(role);
    }
}
