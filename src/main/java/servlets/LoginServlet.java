package servlets;

import database.DBConnection;
import database.User;
import database.UserPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maria on 1/5/17.
 */

//@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.println("Session " + session);
        if (session != null) {
            Map<String, String> messages = new HashMap<String, String>();
            request.setAttribute("messageMap", messages);
            String uname = (String)session.getAttribute("name");
            System.out.println("Uname " + uname);
            messages.put("userName", uname);
            System.out.println("wrong place");
            request.getRequestDispatcher("/hello.jsp").forward(request, response);

        } else {
            System.out.println("wright place");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messageMap", messages);
        String login = request.getParameter("uname");
        String password = request.getParameter("pass");

        if (login.equals("")) {
            messages.put("login", "Please enter login");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        if (password.equals("")) {
            messages.put("passwd", "Please enter password");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        DBConnection Connection = new DBConnection();
        try {
            Connection.connect();
            UserPassword user = Connection.getUser(login);
            if (user.getUsername() == null ||  !user.getPassword().equals(password)) {
                messages.put("success", "Your login or password are incorrect");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            if (messages.isEmpty()) {
                messages.put("userName", login);
                System.out.println("Was I here? 1");
                messages.put("success", String.format("Hello %s ! Your login is %s and password is %s", login, login, password));
                HttpSession session = request.getSession();
                session.setAttribute("name", login);
                request.getRequestDispatcher("/hello.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
