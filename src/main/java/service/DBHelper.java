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

    final static Logger logger = Logger.getLogger(DBHelper.class.getName());

    private DBHelper() throws DBException {
        this.connection = getMySQLConnection();
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

}
