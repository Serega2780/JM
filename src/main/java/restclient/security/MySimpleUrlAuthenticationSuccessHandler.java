package restclient.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import restclient.domain.Role;
import restclient.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User principal = (User) authentication.getPrincipal();

        Collection<Role> roles = principal.getGrantedAuthorities();

        for (Role role : roles) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                httpServletResponse.sendRedirect("admin/list");
                return;
            }
        }
        httpServletResponse.sendRedirect("user/list");
    }
}
