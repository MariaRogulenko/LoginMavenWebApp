package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 1/13/17.
 */
public class DBConnection {
    String dbUrl = "jdbc:mysql://localhost:3306/mavenwebproject";
    String username = "root";
    String password = "maria";
    Connection connection;

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
        connection = DriverManager.getConnection(dbUrl, username, password);
        if (connection != null) {
            System.out.println("System connected!");
        } else {
            System.out.println("Ops, something went wrong :(");
        }
    }

    public void createUser(String uname, String upassword, String uaddress) throws SQLException {
        String sql = "INSERT INTO users(login, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, uname);
        statement.setString(2, upassword);
        int rowInsert = statement.executeUpdate();
        if (rowInsert > 0) {
            System.out.println("newUserInserted");
        } else {
            System.out.println("Ops! User was not inserted.");
        }
        if (uaddress != null) {
            String sql2 = "INSERT INTO userinfo(login, address) VALUES (?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, uname);
            statement2.setString(2, uaddress);
            int rowInsert2 = statement2.executeUpdate();
        }


    }

    public User getUser(String uname) throws SQLException {
        String sqlRequest = "SELECT * FROM users WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sqlRequest);
        statement.setString(1, uname);
        ResultSet resultSet = statement.executeQuery();
        User user = new User();
        if (resultSet.next()) {
            user.setUsername(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    public ArrayList<User> getUserList() throws SQLException {
        String sql = "SELECT users.login, users.password, userinfo.address FROM users inner join userinfo where users.login = userinfo.login";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> userArrayList = new ArrayList<User>();
        User user;
        while (resultSet.next()) {
            user = new User();
            user.setUsername(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setAddress(resultSet.getString("address"));
            userArrayList.add(user);
        }
        return userArrayList;
    }

    public void deleteUser(String uname) throws SQLException {
        String sql = "DELETE FROM userinfo WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, uname);
        int rowInsert = statement.executeUpdate();

        String sql2 = "DELETE FROM users WHERE login = ?";
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setString(1, uname);
        int rowInsert2 = statement2.executeUpdate();
    }

    public void updateUser(String uname, String upassword, String uaddress) throws SQLException {
        String sql = "UPDATE userinfo SET address = ? WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, uaddress);
        statement.setString(2, uname);
        int rowInsert = statement.executeUpdate();

        String sql2 = "UPDATE users SET password = ? WHERE login = ?";
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setString(1, upassword);
        statement2.setString(2, uname);
        int rowInsert2 = statement2.executeUpdate();
    }

}
