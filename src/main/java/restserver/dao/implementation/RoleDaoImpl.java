package restserver.dao.implementation;

import org.springframework.stereotype.Repository;
import restclient.domain.Role;
import restserver.dao.RoleDao;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashSet;

import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRole(int id) {

        return em.find(Role.class, id);

    }

    @Override
    public Set<Role> getRoles() {
        return new HashSet<>(em.createQuery("Select r from " + Role.class.getName() + " r ", Role.class)
                .getResultList());
    }

    @Override
    public Role getRoleByName(String role) {
        return em.createQuery("Select r from " + Role.class.getName() + " r " + "Where r.role = :role", Role.class)
                .setParameter("role", role).getSingleResult();
    }
}
