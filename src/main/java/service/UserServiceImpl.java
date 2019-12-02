package service;

import DAO.UserDAO;
import model.User;

import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) {
        try {
            userDAO.insertUser(user);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public User selectUser(int id) {
        try {
            return userDAO.selectUser(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        try {
            return userDAO.selectAllUsers();
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            return userDAO.deleteUser(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }

    }

}
