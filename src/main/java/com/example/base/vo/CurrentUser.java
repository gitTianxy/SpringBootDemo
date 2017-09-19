package com.example.base.vo;

import com.example.base.bo.MyUserDetail;
import com.example.base.service.UserService;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kevintian on 2017/9/19.
 */
public class CurrentUser {
    MyUserDetail userDetail;

    public CurrentUser(MyUserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Long getId() {
        return new UserService().getUserByName(userDetail.getUsername()).getId();
    }

    public String getName() {
        return userDetail.getUsername();
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority authority : userDetail.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
        return roles;
    }
}
