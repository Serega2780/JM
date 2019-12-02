package DAO;

import model.User;
import service.DBException;

import java.util.List;

public interface UserDAO {

    void insertUser(User user) throws DBException;

    User selectUser(int id) throws DBException;

    List<User> selectAllUsers() throws DBException;

    List<User> selectNotAdmins() throws DBException;

    User selectUserByRole(String name, String password) throws DBException;

    boolean deleteUser(int id) throws DBException;

    void updateUser(User user) throws DBException;
}
