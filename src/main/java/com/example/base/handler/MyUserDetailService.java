package com.example.base.handler;

import com.example.base.bo.MyUserDetail;
import com.example.base.domain.User;
import com.example.base.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by kevintian on 2017/9/13.
 */
@Service
public class MyUserDetailService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    UserService userService;

    /**
     * method for user login
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.getUserByName(username);
        if (u == null) {
            logger.info("user not found. name={}", username);
            throw new UsernameNotFoundException("name not found. name=" + username);
        }
        MyUserDetail login = new MyUserDetail(u);
        logger.info("login succ. {}", login);
        return login;
    }

}
