package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.client.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    static String loginUser;

    @FXML
    private TextField pf_login;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Label WRONG;

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

        stage.setOpacity(0.5);
    }

    @FXML
    void undragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.setOpacity(1);
    }

    @FXML
    void login(MouseEvent event) throws IOException {

        if (new Database().checkAcount(pf_login.getText(), pf_password.getText())){
            loginUser = pf_login.getText();

            try {
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/forms/MainWindow.fxml"));
                Parent root = loader.load() ;
//                FXMLLoader.load(getClass().getResource("/forms/MainWindow.fxml"))
                MainSceneController mainScene = loader.getController();
                mainScene.getNickname(pf_login.getText());

                Node node = (Node) event.getSource();

                Stage stage = (Stage) node.getScene().getWindow();

                stage.setScene(new Scene(root));
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("Разрешено");
        }else {
            System.out.println("Запрещено");
            WRONG.setVisible(true);
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
