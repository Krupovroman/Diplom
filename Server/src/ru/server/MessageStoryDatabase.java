package ru.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageStoryDatabase {
    String url = "jdbc:mysql://localhost:3306/httpserverdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String username = "root";
    String password = "root";
    Map messages = new HashMap<Integer, String>();
    ArrayList message = new ArrayList<String>();


    void addMessage (String from, String to, String message){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO messagestory VALUES(" + from + "," + to + "," + message + ");");
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    ArrayList getMessages (String from, String to){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT message FROM messagestory WHERE" +
                    " (`from` == "+ from +" AND `to` == "+ to +") OR (`from` == "+ to +" AND `to` == "+ from +")");
            while (resultSet.next()){
                message.add(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return message;
    }
}
