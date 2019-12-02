package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (((User)req.getSession().getAttribute(req.getSession().getId())).getRole().equals("admin")) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(((HttpServletRequest) request).getContextPath() + "/user");
        }
    }

    @Override
    public void destroy() {

    }

}