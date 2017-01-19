package servlets;

import database.DBConnection;
import database.User;
import database.UserPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by maria on 1/5/17.
 */
//@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messageMap", messages);
        String login = request.getParameter("uname");
        String pass1 = request.getParameter("upass1");
        String pass2 = request.getParameter("upass2");
        String address = request.getParameter("uaddress");
        if (login.equals("")) {
            messages.put("login", "Please enter login");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        if (pass1.equals("") || pass2.equals("")) {
            messages.put("passwd", "Please enter password");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        if (!pass1.equals(pass2)) {
            messages.put("pass", "passwords doesn't match");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

        DBConnection Connection = new DBConnection();
        try {
            Connection.connect();
            UserPassword user = Connection.getUser(login);
            if (user.getUsername() != null) {
                messages.put("register", "login already exists");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            if (messages.isEmpty()) {
                Connection.createUser(login, pass1, address);
                messages.put("userName", login);
                messages.put("success", String.format("Hello %s ! Your login is %s and password is %s", login, login, pass1));
                request.getRequestDispatcher("/hello.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
