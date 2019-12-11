package springhibernatemysql.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springhibernatemysql.dao.UserDao;
import springhibernatemysql.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public User getUserByName(String name) {
        User user;

        user = em.createQuery("Select u from " + User.class.getName() + " u " + "Where u.name = :name", User.class)
                .setParameter("name", name).getSingleResult();
        return user;
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("Select u from " + User.class.getName() + " u ", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users;

        users = em.createQuery("select user from " + User.class.getName() + " " +
                        "user Where user not in ( select u from " + User.class.getName() +
                        " u join u.grantedAuthorities rs  Where rs.role = 'ROLE_ADMIN' )",
                User.class).getResultList();

        return users;

    }

    @Override
    public void removeUser(int id) {

        em.remove(this.getUserById(id));
    }

    @Override
    public void addUser(User user) {

        if (user.getId() == 0) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        em.flush();

    }
}
