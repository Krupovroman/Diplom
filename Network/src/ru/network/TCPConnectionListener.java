package ru.network;


public interface TCPConnectionListener {

    void onConnectionReady(TCPConnection topConnection);
    void onReceiveString(TCPConnection topConnection, String value);
    void onDisconnect(TCPConnection tcpConnection);
    void onException(TCPConnection tcpConnection, Exception e);
}
