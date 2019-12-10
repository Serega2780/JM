package org.spring.mvc.security;

import org.spring.mvc.domain.Role;
import org.spring.mvc.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomSimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User principal = (User) authentication.getPrincipal();
        List<Role> roles = (List<Role>) principal.getAuthorities();

        for (Role role : roles) {
            if (role.getAuthority().contains("ADMIN")) {
                //equals("ROLE_ADMIN")
                httpServletResponse.sendRedirect("/admin/list");
                return;
            }
        }
        httpServletResponse.sendRedirect("/user");
    }
}
