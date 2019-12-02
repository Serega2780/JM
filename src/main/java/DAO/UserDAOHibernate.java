package DAO;

import model.User;
import org.hibernate.*;
import service.DBException;

import java.util.List;

public class UserDAOHibernate implements UserDAO {
    private SessionFactory sessionFactory;
    private Session session;

    public UserDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
    }

    @Override
    public void insertUser(User user) throws DBException {
        session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBException("An error during save operation...");
        } finally {
            session.close();
        }
    }

    @Override
    public User selectUser(int id) throws DBException {
        session = this.sessionFactory.openSession();
        User user;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", id);
        try {
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBException("An error during select operation...");
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public List<User> selectAllUsers() throws DBException {
        session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers;
        try {
            allUsers = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBException("An error during select all operation...");
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public boolean deleteUser(int id) throws DBException {
        session = this.sessionFactory.openSession();
        boolean rowDeleted;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        try {
            rowDeleted = query.executeUpdate() > 0;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBException("An error during delete operation...");
        } finally {
            session.close();
        }
        return rowDeleted;
    }

    @Override
    public void updateUser(User user) throws DBException {
        session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DBException("An error during update operation...");
        } finally {
            session.close();
        }

    }
}
