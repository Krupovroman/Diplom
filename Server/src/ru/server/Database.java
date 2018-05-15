package ru.server;

import java.sql.*;

public class Database {
    String url = "jdbc:mysql://localhost:3306/httpserverdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String username = "root";
    String password = "root";


    void addNewAccount(String login, String pass){/*Создание нового аккаунта*/
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO usersdatabase VALUES(" + login + ", " + login + ", " + pass + ", " + "test" + ");");
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    boolean checkOnFree(String login){/*Проверка на уникальность логина*/
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usersdatabase WHERE login == "+login+"");
            if (resultSet.next()){
                resultSet.close();
                statement.close();
                connection.close();
                return false;
            }else {
                resultSet.close();
                statement.close();
                connection.close();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    void changePassword(String login, String newPass){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
