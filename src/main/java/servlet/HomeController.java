package servlet;

import DAO.UserDAOHibernate;
import DAO.UserDAOJdbc;
import model.User;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/")
public class HomeController extends HttpServlet {

    private UserService userService;

    public void init() {
//        userService = new UserServiceImpl(new UserDAOJdbc(DBHelper.getInstance().getConnection()));
        userService = new UserServiceImpl(new UserDAOHibernate(DBHelper.getInstance().getSessionFactory()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> listUser = userService.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }


}
