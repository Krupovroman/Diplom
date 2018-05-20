package ru.server;

import ru.network.TCPConnection;
import ru.network.TCPConnectionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer implements TCPConnectionListener {
    public static void main(String args[]) {
        new ChatServer();
    }

    ArrayList<TCPConnection> connections = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();

    public ChatServer() {
        System.out.println("Server running");
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection topConnection) {
        connections.add(topConnection);
//        sendAllConnections("Client connected: " + topConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection topConnection, String value) {
        sendAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
//        sendAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exteption: " + e);
    }

    private void sendAllConnections(String value) {
        System.out.println(value);
        final int size = connections.size();
        if (value.length() > 9 && value.substring(0, 9).equals("Connected") && !users.contains(value.substring(9))) {
            users.add(value.substring(9));

            for (int j = 0; j < users.size(); j++) {
                for (int i = 0; i < size; i++) {
                    connections.get(i).sendMessage("newUser" + users.get(j));
                }
            }
        }
        else {
            if (value.length() > 12 && value.substring(0, 12).equals("Disconnected")) {
                users.remove(value.substring(12));

                for (int i = 0; i < size; i++) {
                    connections.get(i).sendMessage("removeUser" + value.substring(12));
                }
//
//                for (int j = 0; j < users.size(); j++) {
//                    for (int i = 0; i < size; i++) {
//                        connections.get(i).sendMessage("newUser" + users.get(j));
//                    }
//                }
            }
            else {
                for (int i = 0; i < size; i++) connections.get(i).sendMessage(value);
            }
        }
    }
}
