package restclient.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import restserver.dao.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {

        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return this.userDao.getUserByName(userName);

    }
}
