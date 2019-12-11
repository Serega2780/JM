package org.spring.mvc.service.impl;

import org.spring.mvc.dao.UserDao;
import org.spring.mvc.domain.User;
import org.spring.mvc.service.DBException;
import org.spring.mvc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private UserDao userDao;

    @Resource(name = "userDaoEntity")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        try {
            userDao.insertUser(user);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public User selectUser(int id) {
        try {
            return userDao.selectUser(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        try {
            return userDao.selectAllUsers();
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> selectNotAdmins() {
        try {
            return userDao.selectNotAdmins();
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public User selectUserByName(String name) {
        try {
            return userDao.selectUserByName(name);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    public String[] selectCountries() {

        return new String[]{
                "Russia", "Portugal", "USA", "France", "Belgium", "Spain"
        };
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            return userDao.deleteUser(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }

    }
}
