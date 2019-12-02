package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;


        User tempUser = (User) req.getSession().getAttribute(req.getSession().getId());

        if (tempUser != null || req.getRequestURI().endsWith("/login") && req.getMethod().equals("POST")) {
            if (req.getRequestURI().endsWith("/login") && req.getMethod().equals("GET")) {
                res.sendRedirect("admin/list");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("index.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }



    }

    @Override
    public void destroy() {

    }
}
