package org.spring.mvc.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.spring.mvc.domain.User;
import org.spring.mvc.service.DBException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    private Session session;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertUser(User user) throws DBException {
        session = sessionFactory.getCurrentSession();
        try {
            session.persist(user);
            session.flush();
        } catch (HibernateException e) {
            throw new DBException("An error during save operation...");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User selectUser(int id) throws DBException {
        session = sessionFactory.getCurrentSession();
        User user;
        Query<User> query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        try {
            user = query.getSingleResult();

        } catch (HibernateException e) {
            throw new DBException("An error during select operation...");
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> selectAllUsers() throws DBException {
        session = sessionFactory.getCurrentSession();
        List<User> allUsers;
        try {
            allUsers = session.createQuery("FROM User").list();

        } catch (HibernateException e) {
            throw new DBException("An error during select all operation...");
        }
        return allUsers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> selectNotAdmins() throws DBException {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE role = :role");
        query.setParameter("role", "user");
        List<User> notAdmins;
        try {
            notAdmins = query.list();

        } catch (HibernateException e) {
            throw new DBException("An error during select all operation...");
        }
        return notAdmins;
    }

    @Override
    @Transactional(readOnly = true)
    public User selectUserByRole(String name, String password) throws DBException {
        session = sessionFactory.getCurrentSession();
        User user = null;
        Query query = session.createQuery("FROM User WHERE name = :name AND password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);
        try {
            if (!query.list().isEmpty()) {
                user = (User) query.list().get(0);
            }

        } catch (HibernateException e) {
            throw new DBException("An error during select by role operation...");
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) throws DBException {
        session = sessionFactory.getCurrentSession();
        boolean rowDeleted;
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
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
        session = sessionFactory.getCurrentSession();
        try {
            session.update(user);
            session.flush();
        } catch (HibernateException e) {
            throw new DBException("An error during save operation...");
        }
    }
}
