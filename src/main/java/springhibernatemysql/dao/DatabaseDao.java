package springhibernatemysql.dao;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DatabaseDao {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        //   this.fullFillTables();

    }

    public DatabaseDao(EntityManager em) {
        this.em = em;
    }

    public void fullFillTables() {
        System.out.println("DatabaseDao has started!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('aaa', 'aaa', 'a@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('a2', 'a2', 'a2@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('a3', 'a3', 'a3@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('uuu', 'uuu', 'u@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('u2', 'u2', 'u2@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('u3', 'u3', 'u3@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  users (name, password, email, country) " +
                "values ('u4', 'u4', 'u4@mail.ru', 'USA');").executeUpdate();

        em.createNativeQuery("insert into  roles (role) " +
                "values ('ROLE_ADMIN');").executeUpdate();

        em.createNativeQuery("insert into  roles (role) " +
                "values ('ROLE_USER');").executeUpdate();

        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (1, 1);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (2, 1);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (3, 1);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (1, 2);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (2, 2);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (3, 2);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (4, 2);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (5, 2);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (6, 2);").executeUpdate();
        em.createNativeQuery("insert into  users_roles (User_Id, Role_Id) " +
                "values (7, 2);").executeUpdate();

        System.out.println("DatabaseDao done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
