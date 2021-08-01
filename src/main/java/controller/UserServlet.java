package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create":
                showCreateUser(req, resp);
                break;
            case "update":
                showUpdateUser(req, resp);
                break;
            case "delete":
                deleteUser(req, resp);
                break;
            case "findByName":
                findUserByName(req, resp);
                break;
            default:
                showUser(req, resp);
                break;

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create":
                createUser(req, resp);
                break;
            case "update":
                updateUser(req, resp);
                break;
        }
    }

    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        RequestDispatcher rd = req.getRequestDispatcher("view/ShowUser.jsp");
        rd.forward(req, resp);
    }

    private void showCreateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("view/CreateUser.jsp");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);
        resp.sendRedirect("/user");
    }

    private void showUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        RequestDispatcher rd = req.getRequestDispatcher("/view/UpdateUser.jsp");
        req.setAttribute("userOld", userDAO.selectUser(id));
        rd.forward(req, resp);
    }

    private void findUserByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fName = req.getParameter("fName");
        List<User> listUser = userDAO.findUserByName(fName);
        req.setAttribute("listUser", listUser);
        RequestDispatcher rd = req.getRequestDispatcher("view/ShowUser.jsp");
        rd.forward(req, resp);
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(name, email, country);
        userDAO.insertUser(user);
        resp.sendRedirect("/user");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        userDAO.updateUser(user);
        resp.sendRedirect("/user");
    }



}
