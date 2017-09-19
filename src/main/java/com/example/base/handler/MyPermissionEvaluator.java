package com.example.base.handler;

import com.example.base.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    private final static Logger logger = LoggerFactory.getLogger(MyPermissionEvaluator.class);
    static Map<String, List<String>> rolePermissionMap = new HashMap<>();
    @Autowired
    UserService userService;

    {
        List<String> visitorMap = new ArrayList<>();
        visitorMap.add(Target.HELLO.toString() + ":" + Permission.READ.toString());
        rolePermissionMap.put(Role.VISITOR.toString(), visitorMap);
        List<String> userMap = new ArrayList<>();
        userMap.add(Target.HELLO.toString() + ":" + Permission.READ.toString());
        userMap.add(Target.SELF.toString() + ":" + Permission.READ.toString());
        userMap.add(Target.SELF.toString() + ":" + Permission.WRITE.toString());
        rolePermissionMap.put(Role.USER.toString(), userMap);
        List<String> adminMap = new ArrayList<>();
        adminMap.add(Target.HELLO.toString() + ":" + Permission.READ.toString());
        adminMap.add(Target.SELF.toString() + ":" + Permission.READ.toString());
        adminMap.add(Target.SELF.toString() + ":" + Permission.WRITE.toString());
        adminMap.add(Target.ALL.toString() + ":" + Permission.READ.toString());
        adminMap.add(Target.ALL.toString() + ":" + Permission.WRITE.toString());
        rolePermissionMap.put(Role.ADMIN.toString(), adminMap);
    }

    /**
     * permission check
     *
     * @param authentication: loginer, wrapped by spring-security
     * @param target:         permission target
     * @param permission:     action that loginer perform on the target
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        logger.info("login:{}, target:{}, permission:{}", authentication.getName(), target, permission);

        Set<String> allowedPermissions = new HashSet<>();
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority role : roles) {
            allowedPermissions.addAll(rolePermissionMap.get(role.getAuthority()));
        }
        if (allowedPermissions.contains(String.format("%s:%s", target, permission))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object
            permission) {
        return false;
    }

    enum Role {
        VISITOR, USER, ADMIN
    }


    enum Permission {
        READ, WRITE
    }

    enum Target {
        HELLO, SELF, ALL
    }
}
