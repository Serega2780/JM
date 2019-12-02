package service;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.PropertyReader;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBHelper {
    /*Singleton*/
    private static DBHelper INSTANCE;
    private final Connection connection;
    private final Configuration configuration;
    private final SessionFactory sessionFactory;

    final static Logger logger = Logger.getLogger(DBHelper.class.getName());

    private DBHelper() throws DBException {

        this.connection = getMySQLConnection();
        this.configuration = getMySqlConfiguration();
        this.sessionFactory = createSessionFactory();

    }

    public static DBHelper getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new DBHelper();
            } catch (DBException e) {
                logger.severe("An error during DBHelper instantiation...");
            }
        }
        return INSTANCE;
    }

    public Connection getConnection() {

        return this.connection;
    }

    public Configuration getConfiguration() {

        return this.configuration;
    }

    public SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }

    private SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static Connection getMySQLConnection() throws DBException {
        try {
            Connection connection;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            StringBuilder url = new StringBuilder();
            url.
                    append(PropertyReader.getProperty("DBTYPE")).
                    append("://").        //db type
                    append(PropertyReader.getProperty("HOST")).           //host name
                    append(":").
                    append(PropertyReader.getProperty("PORT")).                //port
                    append("/").
                    append(PropertyReader.getProperty("DBNAME")).          //db name
                    append("?").
                    append(PropertyReader.getProperty("TIMEZONE"));   //timeZone

            logger.info(String.format("URL: %s\n", url));
            connection = DriverManager.getConnection(url.toString(), PropertyReader.getProperty("USER"),
                    PropertyReader.getProperty("PASSWORD"));
            connection.setAutoCommit(false);
            return connection;

        } catch (SQLException e) {
            logger.severe("An error during JDBC connection...");
            e.printStackTrace();
            throw new DBException("An error during JDBC connection...");
        }

    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", PropertyReader.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertyReader.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", PropertyReader.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", PropertyReader.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", PropertyReader.getProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", PropertyReader.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertyReader.getProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    }

}
