package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demoJDBC?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "dung1998";

    private static final String INSERT_USERS = "insert into users (name, email, country) values (?, ?, ?)";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String SELECT_USERS_BY_ID = "select id, name, email, country from users where id = ?";
    private static final String DELETE_USERS = "delete from users where id = ?";
    private static final String UPDATE_USERS = "update users set name = ?, email = ?, country = ? where id = ?";
//    private static final String FIND_USERS_BY_NAME = "select * from users where name like '%" + "?%'";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement pS = connection.prepareStatement(INSERT_USERS);
            pS.setString(1, user.getName());
            pS.setString(2, user.getEmail());
            pS.setString(3, user.getCountry());
            pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement pS = connection.prepareStatement(SELECT_USERS_BY_ID);
            pS.setInt(1, id);
            ResultSet rs = pS.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(name, email, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement pS = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = pS.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement pS = connection.prepareStatement(DELETE_USERS);
            pS.setInt(1, id);
            pS.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement pS = connection.prepareStatement(UPDATE_USERS);
            pS.setString(1, user.getName());
            pS.setString(2, user.getEmail());
            pS.setString(3, user.getCountry());
            pS.setInt(4, user.getId());
            pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findUserByName(String fName) {
        List<User> findUsers = new ArrayList<>();
        String FIND_USERS_BY_NAME = "select * from users where name like '%" + fName + "%'";
        try {
            Connection connection = getConnection();
            PreparedStatement pS = connection.prepareStatement(FIND_USERS_BY_NAME);
            ResultSet rs = pS.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                findUsers.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findUsers;
    }




}
