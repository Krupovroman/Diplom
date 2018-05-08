package ru.server;

import ru.network.TCPConnection;
import ru.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatServer implements TCPConnectionListener{
    public static void main(String args[]){
        new ChatServer();
    }

    ArrayList<TCPConnection> connections = new ArrayList<>();

    public ChatServer(){
        System.out.println("Server running");
        try (ServerSocket serverSocket = new ServerSocket(8189)){
            while (true){
                try {
                    new TCPConnection(this, serverSocket.accept());
                }catch (IOException e){
                    System.out.println("TCPConnection exception: "+ e);
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection topConnection) {
        connections.add(topConnection);
        sendAllConnections("Client connected: " + topConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection topConnection, String value) {
        sendAllConnections(new SimpleDateFormat("hh:mm:ss").format(new Date()) + " " + value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exteption: " + e);
    }

    private void sendAllConnections(String value){
        System.out.println(value);
        final int size = connections.size();
        for (int i=0; i < size; i++)connections.get(i).sendMessage(value);
    }
}
