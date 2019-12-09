package org.spring.mvc.dao;

import org.spring.mvc.util.PropertyReader;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("userDaoFactory")
public class UserDaoFactory {
    private UserDao userDaoEntityManagerImpl;
    private UserDao userDaoSessionFactoryImpl;

    @Resource(name = "userDaoEntity")
    public void setUserDaoEntityManagerImpl(UserDao userDaoEntityManagerImpl) {
        this.userDaoEntityManagerImpl = userDaoEntityManagerImpl;
    }

    @Resource(name = "userDaoSession")
    public void setUserDaoSessionFactoryImpl(UserDao userDaoSessionFactoryImpl) {
        this.userDaoSessionFactoryImpl = userDaoSessionFactoryImpl;
    }

    public UserDao getUserDao() {
        if (PropertyReader.getProperty("hibernate.dbmanager").equalsIgnoreCase("entityManager")) {
            return userDaoEntityManagerImpl;
        } else if (PropertyReader.getProperty("hibernate.dbmanager").equalsIgnoreCase("sessionFactoryManager")) {
            return userDaoSessionFactoryImpl;
        }
        return null;
    }
}
