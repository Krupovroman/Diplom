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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import ru.network.TCPConnection;
import ru.network.TCPConnectionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable, TCPConnectionListener {

    private final String IP_ADDR = "127.0.0.1";
    private int PORT = 8189;
    private TCPConnection connection;
    private String nickname;
    private ArrayList<String> users = new ArrayList<>();


    @FXML
    private VBox textArea;

    @FXML
    private VBox contactsArea;

    @FXML
    private TextField txtMsg;


    @FXML
    private ScrollPane ScrollProperty;

    private double x = 0, y = 0;


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
    void exit(MouseEvent event) {
        connection.sendMessage("Disconnected" + nickname);
        connection.disconnect();
        Node node = (Node) event.getSource();


        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
    }

    @FXML
    void close(MouseEvent event) throws Exception {
        Node node = (Node) event.getSource();


        Stage stage = (Stage) node.getScene().getWindow();

        stage.setIconified(true);

    }


    @FXML
    synchronized void SEND(ActionEvent event) throws Exception {
        String message;
        if (nickname.length() >= 10) {
            message = nickname.length() + ":" + nickname + txtMsg.getText();
        } else {
            message = "0" + nickname.length() + nickname + txtMsg.getText();
        }
        connection.sendMessage(message);
        ScrollProperty.vvalueProperty().bind(textArea.heightProperty());
        txtMsg.clear();
    }

    private synchronized void getMessage(String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (msg.length() > 7 && msg.substring(0, 7).equals("newUser")) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        Parent rootNode = null;
                        rootNode = loader.load(getClass().getResource("/forms/Contact.fxml"));
                        Label text = (Label) rootNode.lookup("#contactName");
                        Circle avatar = (Circle) rootNode.lookup("#avatar");
                        avatar.setFill(new ImagePattern(new Image("https://cdn2.iconfinder.com/data/icons/website-icons/512/User_Avatar-512.png")));
                        if (!users.contains(msg.substring(7))) {
                            users.add(msg.substring(7));
                            text.setText(msg.substring(7));
                            contactsArea.getChildren().addAll(new VBox(rootNode));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (msg.length() > 10 && msg.substring(0, 10).equals("removeUser")) {
                        try {
                            if (users.contains(msg.substring(10))) {
                                users.remove(msg.substring(10));
                                contactsArea.getChildren().clear();
                                for (int i = 0; i < users.size(); i++) {
                                    repaint(users.get(i));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        FXMLLoader loader = new FXMLLoader();
                        Parent rootNode = null;
                        Parent root = null;
                        String nicknameFromMessage = msg.substring(2, Integer.parseInt(msg.substring(0, 2)) + 2);//побыдлокодим немножечко
                        try {
                            if (nickname.equals(nicknameFromMessage)) {
                                rootNode = loader.load(getClass().getResource("/forms/Message.fxml"));
                                FXMLLoader load = new FXMLLoader(getClass().getResource("/forms/Login.fxml"));
                            } else {
                                rootNode = loader.load(getClass().getResource("/forms/MessageFrom.fxml"));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String message = msg.substring(nicknameFromMessage.length() + 2);
                        TextArea text = (TextArea) rootNode.lookup("#textMessage");
                        Label nick = (Label) rootNode.lookup("#nicknameMessage");
                        nick.setText(nicknameFromMessage);
                        Label time = (Label) rootNode.lookup("#timeMessage");
                        Circle avatar = (Circle) rootNode.lookup("#avatar");
                        avatar.setFill(new ImagePattern(new Image("https://cdn2.iconfinder.com/data/icons/website-icons/512/User_Avatar-512.png")));
                        text.setText(message);
                        time.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                        VBox box = new VBox(rootNode);
                        if (nickname.equals(nicknameFromMessage)) {
                            box.setAlignment(Pos.TOP_RIGHT);
                        } else {
                            box.setAlignment(Pos.TOP_LEFT);
                        }
                        textArea.getChildren().addAll(box);
                    }
                }
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    connection.sendMessage("Connected" + nickname);
                }
            });
        } catch (IOException e) {
        }
    }

    void repaint(String userName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent rootNode = null;
            rootNode = loader.load(getClass().getResource("/forms/Contact.fxml"));
            Label text = (Label) rootNode.lookup("#contactName");
            Circle avatar = (Circle) rootNode.lookup("#avatar");
            avatar.setFill(new ImagePattern(new Image("https://cdn2.iconfinder.com/data/icons/website-icons/512/User_Avatar-512.png")));
            text.setText(userName);
            contactsArea.getChildren().addAll(new VBox(rootNode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getNickname(String nickFromLoginController) {
        nickname = nickFromLoginController;
    }

    @Override
    public void onConnectionReady(TCPConnection topConnection) {
    }

    @Override
    public void onReceiveString(TCPConnection topConnection, String value) {
        getMessage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }
}
