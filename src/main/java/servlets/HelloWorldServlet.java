package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * Created by maria on 1/4/17.
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException  {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<H1> Hello, World! </H1>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        String user = req.getParameter("uname");
        String password = req.getParameter("upassword");
        PrintWriter pw = resp.getWriter();
        pw.println("<h1> Welcome, " + user + "! </h1>");
        pw.println("<h1> Your password is " + password + " </h1>");

    }

}
