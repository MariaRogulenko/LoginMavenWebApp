package servlets;

import database.DBConnection;
import database.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by maria on 1/16/17.
 */
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBConnection Connection = new DBConnection();
        try {
            Connection.connect();
            ArrayList<User> list = Connection.getUserList();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getUserPass().getUsername());
                System.out.println(list.get(i).getUserPass().getPassword());
                System.out.println(list.get(i).getUserAdd().getAddress());
            }
            request.setAttribute("personList", list);
            request.getRequestDispatcher("/update.jsp").forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("uname");
        String password = request.getParameter("upass");
        String address = request.getParameter("uaddress");
        String save = request.getParameter("save");
        String delete = request.getParameter("delete");

        System.out.println(login);
        System.out.println(password);
        System.out.println(address);
        System.out.println(save);
        System.out.println(delete);

        DBConnection Connection = new DBConnection();
        try {
            Connection.connect();
            if (save != null) {
                Connection.updateUser(login, password, address);
                response.setHeader("Refresh", "0; URL=" + request.getContextPath() + "/update");

            } else if (delete != null) {
                Connection.deleteUser(login);
                response.setHeader("Refresh", "0; URL=" + request.getContextPath() + "/update");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (save != null) {

        } else if (delete != null) {

        }

    }
}
