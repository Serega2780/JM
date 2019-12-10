package org.spring.mvc.dao;

import org.spring.mvc.domain.User;
import org.spring.mvc.service.DBException;

import java.util.List;

public interface UserDao {

    void insertUser(User user) throws DBException;

    User selectUser(int id) throws DBException;

    List<User> selectAllUsers() throws DBException;

    List<User> selectNotAdmins() throws DBException;

    List<String> selectCountries() throws DBException;

    User selectUserByName(String name) throws DBException;

    boolean deleteUser(int id) throws DBException;

    void updateUser(User user) throws DBException;
}
