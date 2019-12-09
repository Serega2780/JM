package org.spring.mvc.dao;

import org.hibernate.HibernateException;
import org.spring.mvc.domain.User;
import org.spring.mvc.service.DBException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Repository("userDaoEntity")
public class UserDaoEntityManagerImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertUser(User user) throws DBException {
       try {
            entityManager.persist(user);
            entityManager.flush();
        } catch (HibernateException e) {
            throw new DBException("An error during insert operation...");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User selectUser(int id) throws DBException {
        User user;
        try {
            user = entityManager.find(User.class, id);
        } catch (HibernateException | NoResultException | ClassCastException e) {
            throw new DBException("An error during select user by id operation...");
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> selectAllUsers() throws DBException {
        List<User> allUsers;
        try {
            allUsers = entityManager.createQuery("FROM User").getResultList();
        } catch (HibernateException | NoResultException | ClassCastException e) {
            throw new DBException("An error during select user by id operation...");
        }
        return allUsers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> selectNotAdmins() throws DBException {
        List<User> users;
        Query query = entityManager.createQuery("FROM User where role = :role ");
        query.setParameter("role", "user");
        try {
            users = query.getResultList();
        } catch (HibernateException | NoResultException | ClassCastException e) {
            throw new DBException("An error during select not admins operation...");
        }
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User selectUserByRole(String name, String password) throws DBException {
        User user;
        Query query = entityManager.createQuery("FROM User WHERE name = :name AND password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);
        try {
            user = (User) query.getSingleResult();
        } catch (HibernateException e) {
            throw new DBException("An error during select user by role operation...");
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) throws DBException {
        boolean rowDeleted;
        Query query = entityManager.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        try {
            rowDeleted = query.executeUpdate() > 0;
        } catch (HibernateException e) {
            throw new DBException("An error during delete operation...");
        }
        return rowDeleted;
    }

    @Override
    public void updateUser(User user) throws DBException {
        try {
            entityManager.merge(user);
            entityManager.flush();
        } catch (HibernateException e) {
            throw new DBException("An error during update operation...");
        }
    }
}
