package service;

import model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User selectUser(int id);

    List<User> selectAllUsers();

    boolean deleteUser(int id);

    void updateUser(User user);
}
