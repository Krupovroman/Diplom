package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.client.Database;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable{

    @FXML
    private HBox messageBox;

    @FXML
    private TextArea textMessage;

    @FXML
    private VBox textArea;

    @FXML
    private VBox contactsArea;

    @FXML
    private TextField txtMsg;

    @FXML
    private AnchorPane chatPane;

    double x = 0, y = 0;
    String textForChat;

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
//        s.setFill(Color.TRANSPARENT);
    }

    @FXML
    void undragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.setOpacity(1);
    }


    @FXML
    void exit(MouseEvent event) {
        Node node = (Node) event.getSource();


        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    @FXML
    void close(MouseEvent event) throws Exception{
//        Node node = (Node) event.getSource();
//
//
//        Stage stage = (Stage) node.getScene().getWindow();
//
//        stage.setIconified(true);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = loader.load(getClass().getResource("/forms/Contact.fxml"));
        Label text = (Label) rootNode.lookup("#contactName");
        text.setText("Рандом");
        contactsArea.getChildren().addAll(new VBox(rootNode));
    }

    @FXML
    void SEND(ActionEvent event) throws Exception{
//        textArea.getChildren().add(messageBox);
//        HBox newLoadedPane =  FXMLLoader.load(getClass().getResource("/forms/Message.fxml"));
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = loader.load(getClass().getResource("/forms/Message.fxml"));
        TextArea text = (TextArea) rootNode.lookup("#textMessage");
        text.setText(txtMsg.getText());
        textArea.getChildren().addAll(new VBox(rootNode));
        txtMsg.clear();
    }


//        Parent root = FXMLLoader.load(getClass().getResource("/forms/login.fxml"));
//
//        Node node = (Node) event.getSource();
//
//        Stage stage = (Stage) node.getScene().getWindow();
//
//        stage.setScene(new Scene(root));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
