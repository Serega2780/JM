package springhibernatemysql.service;

import org.springframework.stereotype.Repository;

import springhibernatemysql.domain.User;

import java.util.List;

@Repository
public interface UserService {
    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(int id);

    User getUserById(int id);

    List<User> selectUsersByRole();

}
