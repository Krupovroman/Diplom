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
import ru.client.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField pf_login;

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
    void login(MouseEvent event) throws IOException {

        if (new Database().checkAcount(pf_login.getText(), pf_password.getText())){
            Parent root = FXMLLoader.load(getClass().getResource("/forms/MainWindow.fxml"));

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            System.out.println("Разрешено");
        }else {
            System.out.println("Запрещено");
        }



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
    void signup(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/forms/SignUp.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
