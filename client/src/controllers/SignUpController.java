package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.client.Client;
import ru.client.Database;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField pf_login;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;

    double x = 0, y = 0;

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void login(MouseEvent event) throws Exception {



        Parent root = FXMLLoader.load(getClass().getResource("/forms/login.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    void exit(MouseEvent event) {
        Node node = (Node) event.getSource();


        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    @FXML
    void close (MouseEvent event) {
        Node node = (Node) event.getSource();


        Stage stage = (Stage) node.getScene().getWindow();

        stage.setIconified(true);
    }

    @FXML
    void signup(MouseEvent event) throws IOException {
        if(new Database().checkOnFree(pf_login.getText())) {
            new Database().addNewAccount(pf_login.getText(), pf_password.getText());
            System.out.println("OK");
        }else {
            System.out.println("NE OK");
        }

//        Parent root = FXMLLoader.load(getClass().getResource("/forms/login.fxml"));
//
//        Node node = (Node) event.getSource();
//
//        Stage stage = (Stage) node.getScene().getWindow();
//
//        stage.setScene(new Scene(root));
    }

        @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
