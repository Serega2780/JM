package springhibernatemysql.service.implementation;

import org.springframework.stereotype.Service;

import springhibernatemysql.dao.RoleDao;
import springhibernatemysql.dao.UserDao;

import springhibernatemysql.domain.User;
import springhibernatemysql.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final RoleDao roleDao;
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;

    }

    @Override

    public List<User> getAllUsers() {
        List<User> users;
        users = userDao.getUsers();

        return users;
    }

    @Override
    public void createUser(User user) {

        userDao.addUser(user);


    }

    @Override
    public void deleteUser(int id) {
        userDao.removeUser(id);
    }


    @Override
    public User getUserById(int id) {

        return userDao.getUserById(id);
    }


    @Override
    public List<User> selectUsersByRole() {
        return userDao.getUsersByRole("");

    }

}
