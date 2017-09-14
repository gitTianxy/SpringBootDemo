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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    private final static Logger logger = LoggerFactory.getLogger(MyPermissionEvaluator.class);

    @Autowired
    UserService userService;

    enum Role {
        VISITOR, USER, ADMIN
    }

    enum Permission {
        READ, WRITE
    }

    enum Target {
        HELLO, SELF, ALL
    }

    static Map<String, Map<String, String>> rolePermissionMap = new HashMap<>();
    {
        Map<String, String> visitorMap = new HashMap<>();
        visitorMap.put(Target.HELLO.toString(), Permission.READ.toString());
        rolePermissionMap.put(Role.VISITOR.toString(), visitorMap);
        Map<String, String> userMap = new HashMap<>();
        userMap.put(Target.HELLO.toString(), Permission.READ.toString());
        userMap.put(Target.SELF.toString(), Permission.READ.toString());
        userMap.put(Target.SELF.toString(), Permission.WRITE.toString());
        rolePermissionMap.put(Role.USER.toString(), userMap);
        Map<String, String> adminMap = new HashMap<>();
        adminMap.put(Target.HELLO.toString(), Permission.READ.toString());
        adminMap.put(Target.SELF.toString(), Permission.READ.toString());
        adminMap.put(Target.SELF.toString(), Permission.WRITE.toString());
        adminMap.put(Target.ALL.toString(), Permission.READ.toString());
        adminMap.put(Target.ALL.toString(), Permission.WRITE.toString());
        rolePermissionMap.put(Role.ADMIN.toString(), adminMap);
    }


    /**
     * permission check
     *
     * @param authentication: loginer, wrapped by spring-security
     * @param target: permission target
     * @param permission: action that loginer perform on the target
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
//        String username = authentication.getName();
//        User u = userService.getUserByName(username);
        logger.info("login:{}, target:{}, permission:{}", authentication.getPrincipal(), target, permission);

        Map<String, String> allowedPermissions = new HashMap<>();
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for(GrantedAuthority role : roles) {
            allowedPermissions.putAll(rolePermissionMap.get(role.getAuthority()));
        }
        if (allowedPermissions.get(permission) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
