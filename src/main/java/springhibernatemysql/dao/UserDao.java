package springhibernatemysql.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springhibernatemysql.domain.User;

import java.util.List;


@Repository
@Transactional

public interface UserDao {
    User getUserByName(String name);

    List<User> getUsers();

    User getUserById(int id);

    List<User> getUsersByRole(String role);

    void removeUser(int id);

    void addUser(User user);
}
