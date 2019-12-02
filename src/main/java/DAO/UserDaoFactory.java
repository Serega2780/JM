package DAO;

import service.DBHelper;
import util.PropertyReader;

public class UserDaoFactory {
    public UserDAO getUserDAO() {
        if (PropertyReader.getProperty("DAO").equalsIgnoreCase("JDBC")) {
            return new UserDAOJdbc(DBHelper.getInstance().getConnection());
        } else {
            return new UserDAOHibernate(DBHelper.getInstance().getSessionFactory());
        }
    }
}
