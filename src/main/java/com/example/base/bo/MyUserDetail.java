package com.example.base.bo;

import com.example.base.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * 用于存储登录对象
 */
public class MyUserDetail extends org.springframework.security.core.userdetails.User {
    private User user;

    public MyUserDetail(User user) {
        super(user.getName(), user.getPasswd(), AuthorityUtils.createAuthorityList(user.getRoles()));
        this.user = user;
    }
}
