package ru.server;

import java.sql.*;

public class Database {
    String url = "jdbc:mysql://localhost:3306/httpserverdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String username = "root";
    String password = "root";

    void method(){/*Тестовый метод будет удалено*/
        try {

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usersdatabase");

//                while (resultSet.next()) {
//                    System.out.println(resultSet.getString(1));
//                    System.out.println(resultSet.getString(2));
//                    System.out.println(resultSet.getString(3));
//                    System.out.println(resultSet.getString(4));
//                    System.out.println();
//                }

            statement.executeUpdate("INSERT INTO usersdatabase VALUES('test', 'test', 'test', 'test')");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addNewAccount(String nickname, String login, String pass, String email){/*Создание нового аккаунта*/
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO usersdatabase VALUES(" + nickname + ", " + login + ", " + pass + ", " + email + ");");
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
