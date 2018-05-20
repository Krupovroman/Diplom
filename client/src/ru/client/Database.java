package ru.client;

import java.sql.*;

public class Database {
    String url = "jdbc:mysql://localhost:3306/httpserverdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String username = "root";
    String password = "root";



    public void addNewAccount(String login, String pass){/*Создание нового аккаунта*/
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO usersdatabase VALUES('" + login + "','" + login + "','" + pass + "','" + pass + "','" + null + "');");
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean checkOnFree(String login){/*Проверка на уникальность логина*/
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usersdatabase WHERE login = '"+login+"'");
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

    public boolean checkAcount(String login, String pass){/*Проверка на уникальность логина*/
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usersdatabase WHERE login = '"+login+"' " +
                    "AND password = '"+pass+"' ");
            if (resultSet.next()){
                resultSet.close();
                statement.close();
                connection.close();
                return true;
            }else {
                resultSet.close();
                statement.close();
                connection.close();
                return false;
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
