package ru.server;

import java.sql.*;
import java.util.ArrayList;

public class ContactsDatabase {
    String url = "jdbc:mysql://localhost:3306/httpserverdatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String username = "root";
    String password = "root";
    ArrayList contacts = new ArrayList<String>();
    Boolean flag;

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

    void addContact(String login, String contact){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO contacts VALUES(" + login + "," + contact +");");
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    ArrayList getContacts(String login){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT contact FROM contacts WHERE login == " + login + ")");
            while (resultSet.next()){
                contacts.add(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contacts;
    }

    boolean check (){
        return true;
    }
}
