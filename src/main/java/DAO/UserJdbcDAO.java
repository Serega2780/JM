package DAO;

import model.User;
import service.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private final Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertUser(User user) throws DBException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user" +
                "  (name, email, country) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DBException("An error during rollback in INSERT query...");
            }
            throw new DBException("An error during INSERT query...");
        }
    }

    @Override
    public User selectUser(int id) throws DBException {
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement("select id,name,email,country from " +
                "user where id =?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            throw new DBException("An error during SELECT User query...");
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() throws DBException {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from user")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            throw new DBException("An error during SELECT All query...");
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws DBException {
        boolean rowDeleted = false;
        try (PreparedStatement statement = connection.prepareStatement("delete from user where id = ?;")) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DBException("An error during rollback in DELETE query...");
            }
            throw new DBException("An error during DELETE query...");
        }
        return rowDeleted;
    }

    @Override
    public void updateUser(User user) throws DBException {
        try (PreparedStatement statement = connection.prepareStatement("update user set name = ?,email= ?, " +
                "country =? where id = ?;")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DBException("An error during rollback in UPDATE query...");
            }
            throw new DBException("An error during UPDATE query...");
        }
    }
}
