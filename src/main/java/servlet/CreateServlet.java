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

@WebServlet({"/admin/new", "/admin/insert"})
public class CreateServlet extends HttpServlet {
    UserService userService;

    public void init() {
        userService = new UserServiceImpl(new UserDaoFactory().getUserDAO());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, password, role, email, country);
        userService.createUser(newUser);
        response.sendRedirect("list");

    }
}
