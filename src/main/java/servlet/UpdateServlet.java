package servlet;

import DAO.UserDAOHibernate;
import DAO.UserDAOJdbc;
import DAO.UserDaoFactory;
import model.User;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin/edit", "/admin/update"})
public class UpdateServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserServiceImpl(new UserDaoFactory().getUserDAO());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userService.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id, name, password, role, email, country);
        userService.updateUser(user);
        response.sendRedirect("list");

    }
}
