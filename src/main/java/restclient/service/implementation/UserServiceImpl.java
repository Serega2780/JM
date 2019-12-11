package restclient.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restclient.domain.User;
import restclient.service.UserService;
import restserver.dao.RoleDao;
import restserver.dao.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {

        this.userDao = userDao;
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
