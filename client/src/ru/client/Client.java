package ru.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("../../forms/SignUp.fxml"));

        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}



//public class Client extends JFrame implements ActionListener, TCPConnectionListener{
//
//    private static final String IP_ADDR = "127.0.0.1";
//    private static int PORT = 8189;
//    private static int WIDTH = 600;
//    private static int HEIGHT = 400;
//    public static void main(String args[]){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Client();
//            }
//        });
//    }
//
//    private final JTextArea log = new JTextArea();
//    private final JTextField fieldNickname = new JTextField("Nickname");
//    private final JTextField fieldInput = new JTextField();
//
//    private TCPConnection connection;
//
//    private Client(){
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(WIDTH,HEIGHT);
//        setLocationRelativeTo(null);
//        setAlwaysOnTop(true);
//        log.setEditable(false);
//        log.setLineWrap(true);
//
//        fieldInput.addActionListener(this);
//        add(log, BorderLayout.CENTER);
//        add(fieldInput, BorderLayout.SOUTH);
//        add(fieldNickname, BorderLayout.NORTH);
//
//        setVisible(true);
//
//        try {
//            connection = new TCPConnection(this, IP_ADDR, PORT);
//        } catch (IOException e) {
//            printMessage("Connection exception: " + e);
//        }
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String message = fieldInput.getText();
//        if(message.equals(""))return;
//        fieldInput.setText(null);
//        connection.sendMessage(fieldNickname.getText() + ": " + message);
//    }
//
//
//    @Override
//    public void onConnectionReady(TCPConnection topConnection) {
//        printMessage("Connection ready!");
//    }
//
//    @Override
//    public void onReceiveString(TCPConnection topConnection, String value) {
//        printMessage(value);
//    }
//
//    @Override
//    public void onDisconnect(TCPConnection tcpConnection) {
//        printMessage("Connection close");
//    }
//
//    @Override
//    public void onException(TCPConnection tcpConnection, Exception e) {
//        printMessage("Connection exception: " + e);
//    }
//
//    private synchronized void printMessage(String msg){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                log.append(msg + "\n");
//                log.setCaretPosition(log.getDocument().getLength());
//            }
//        });
//    }
//
//}
