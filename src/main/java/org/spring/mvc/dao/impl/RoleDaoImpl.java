package org.spring.mvc.dao.impl;

import org.hibernate.HibernateException;
import org.spring.mvc.dao.RoleDao;
import org.spring.mvc.domain.Role;
import org.spring.mvc.domain.User;
import org.spring.mvc.service.DBException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void insertRole(Role role) throws DBException {
        try {
            entityManager.persist(role);
            entityManager.flush();
        } catch (HibernateException e) {
            throw new DBException("An error during insert operation...");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Role selectRoleById(int id) throws DBException {
        try {
            return entityManager.find(Role.class, id);
        } catch (HibernateException | NoResultException | ClassCastException e) {
            throw new DBException("An error during select by id  roles operation...");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Role selectRoleByName(String name) throws DBException {
        Role role;
        Query query = entityManager.createQuery("FROM Role WHERE role = :name");
        query.setParameter("name", name);
        try {
            role = (Role) query.getSingleResult();
        } catch (HibernateException e) {
            throw new DBException("An error during select role by name operation...");
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> selectAllRoles() throws DBException {
        List<Role> allRoles;
        try {
            allRoles = entityManager.createQuery("FROM Role").getResultList();
        } catch (HibernateException | NoResultException | ClassCastException e) {
            throw new DBException("An error during select all  roles operation...");
        }
        return allRoles;
    }

    @Override
    public boolean deleteRole(int id) throws DBException {
        try {
            entityManager.remove(selectRoleById(id));
            return true;
        } catch (HibernateException | NoResultException | ClassCastException e) {
            throw new DBException("An error during delete  roles operation...");
        }
    }

    @Override
    public void updateRole(Role role) throws DBException {
        try {
            entityManager.merge(role);
            entityManager.flush();
        } catch (HibernateException e) {
            throw new DBException("An error during update operation...");
        }
    }
}
