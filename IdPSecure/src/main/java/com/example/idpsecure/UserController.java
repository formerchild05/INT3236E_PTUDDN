package com.example.idpsecure;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/")
    public Map<String, Object> getUser(@AuthenticationPrincipal OidcUser oidcUser) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", oidcUser.getSubject());
        userInfo.put("name", oidcUser.getFullName());
        userInfo.put("email", oidcUser.getEmail());
        userInfo.put("claims", oidcUser.getClaims());
        return userInfo;
    }
}

