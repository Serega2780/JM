package servlet;

import DAO.UserDaoFactory;
import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService;

    public void init() {
        userService = new UserServiceImpl(new UserDaoFactory().getUserDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = null;
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        if (!name.isEmpty() && !password.isEmpty()) {
            user = userService.selectUserByRole(name, password);
        }

        if (user != null) {
            request.getSession().setAttribute(request.getSession().getId(), user);
            switch (user.getRole()) {
                case ("admin"):
                    response.sendRedirect("/admin/list");
                    break;
                case ("user"):
                    response.sendRedirect("/user");
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }
}
