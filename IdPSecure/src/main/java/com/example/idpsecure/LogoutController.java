package com.example.idpsecure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/fullLogout")
    public String logout(HttpServletRequest request,
                         @AuthenticationPrincipal OidcUser oidcUser) throws Exception {

        // Lấy id_token từ OidcUser
        String idToken = oidcUser.getIdToken().getTokenValue();

        // Gọi endpoint logout của Keycloak
        String logoutUrl = "http://localhost:8180/realms/myrealm/protocol/openid-connect/logout" +
                "?id_token_hint=" + idToken +
                "&post_logout_redirect_uri=http://localhost:8080";

        // Xóa session ở Spring Boot
        request.logout();

        return "redirect:" + logoutUrl;
    }
}

