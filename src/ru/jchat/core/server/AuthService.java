package ru.jchat.core.server;

import java.sql.*;

public class AuthService {
    private Connection connection;
    private PreparedStatement psFindNick;


    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        psFindNick = connection.prepareStatement("SELECT nick FROM users WHERE login = ? AND password = ?;")
    }

    public String getNickByLoginAndPass(String login, String pass) {
        try {
            psFindNick.setString(1, login);
            psFindNick.setString(2, pass);
            ResultSet rs = psFindNick.executeQuery();
            if (rs.next()){
                return rs.getString("nick");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect(){

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
