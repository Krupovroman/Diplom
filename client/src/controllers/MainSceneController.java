package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.network.TCPConnection;
import ru.network.TCPConnectionListener;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable, TCPConnectionListener, ActionListener {

    private static final String IP_ADDR = "127.0.0.1";
    private static int PORT = 8189;
    private TCPConnection connection;

//    private MainSceneController(){
//        try {
//            connection = new TCPConnection(this, IP_ADDR, PORT);
//        } catch (IOException e) {
////            printMessage("Connection exception: " + e);
//        }
//    }

    @FXML
    private VBox textArea;

    @FXML
    private VBox contactsArea;

    @FXML
    private TextField txtMsg;

    @FXML
    private AnchorPane chatPane;

    @FXML
    private ScrollPane ScrollProperty;

    private double x = 0, y = 0;
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
    synchronized void SEND(ActionEvent event) throws Exception{
//        FXMLLoader loader = new FXMLLoader();
//        Parent rootNode = null;
//        rootNode = loader.load(getClass().getResource("/forms/Message.fxml"));
//        TextArea text = (TextArea) rootNode.lookup("#textMessage");
//        text.setText(txtMsg.getText());
//        VBox box = new VBox(rootNode);
//        box.setAlignment(Pos.TOP_RIGHT);
//        textArea.getChildren().addAll(box);

        connection.sendMessage(txtMsg.getText());
        ScrollProperty.vvalueProperty().bind(textArea.heightProperty());
        txtMsg.clear();
    }

    private synchronized void getMessage(String msg){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader();
                Parent rootNode = null;
                try {
                    rootNode = loader.load(getClass().getResource("/forms/Message.fxml"));
                }catch (Exception e){
                    e.printStackTrace();
                }
                TextArea text = (TextArea) rootNode.lookup("#textMessage");

                text.setText(msg);
//        text.setText(msg);
//        connection.sendMessage(txtMsg.getText());
                VBox box = new VBox(rootNode);
                box.setAlignment(Pos.TOP_RIGHT);
                textArea.getChildren().addAll(box);
            }
        });
//        FXMLLoader loader = new FXMLLoader();
//        Parent rootNode = null;
//        try {
//            rootNode = loader.load(getClass().getResource("/forms/Message.fxml"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        TextArea text = (TextArea) rootNode.lookup("#textMessage");
//
//        text.setText(msg);
////        text.setText(msg);
////        connection.sendMessage(txtMsg.getText());
//        VBox box = new VBox(rootNode);
//        box.setAlignment(Pos.TOP_RIGHT);
//        textArea.getChildren().addAll(box);
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
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
//            printMessage("Connection exception: " + e);
        }
    }

void get(String a){

}

    @Override
    public void onConnectionReady(TCPConnection topConnection) {
    }

    @Override
    public void onReceiveString(TCPConnection topConnection, String value)  {
//        getMessage(value);
        getMessage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {

    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String message = txtMsg.getText();
        if(message.equals(""))return;
        txtMsg.setText(null);
        connection.sendMessage("I'M" + ": " + message);
        System.out.println(message);
    }

//    private synchronized void printMessage(String msg){
//        new Runnable() {
//            @Override
//            public void run() {
//                log.append(msg + "\n");
//                log.setCaretPosition(log.getDocument().getLength());
//            }
//        }
//    }
}
