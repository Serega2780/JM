package org.spring.mvc.service.impl;

import org.spring.mvc.dao.RoleDao;
import org.spring.mvc.domain.Role;
import org.spring.mvc.service.DBException;
import org.spring.mvc.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    private static final Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());

    @Resource(name = "roleDao")
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public void insertRole(Role role) {
        try {
            roleDao.insertRole(role);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public Role selectRoleById(int id) {
        try {
            return roleDao.selectRoleById(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public Role selectRoleByName(String name) {
        try {
            return roleDao.selectRoleByName(name);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Role> selectAllRoles() {
        try {
            return roleDao.selectAllRoles();
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteRole(int id) {
        try {
            return roleDao.deleteRole(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public void updateRole(Role role) {
        try {
            roleDao.updateRole(role);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
    }
}
