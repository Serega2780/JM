package org.spring.mvc.service;

import org.spring.mvc.domain.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User selectUser(int id);

    List<User> selectAllUsers();

    List<User> selectNotAdmins();

    User selectUserByRole(String name, String password);

    boolean deleteUser(int id);

    void updateUser(User user);
}
