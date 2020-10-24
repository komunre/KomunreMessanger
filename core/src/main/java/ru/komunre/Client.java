package ru.komunre;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket client;

    public void connect(String ip, int port) throws IOException {
        client = new Socket(ip, port);
    }

    public void sendMessage(String message) throws IOException {
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        out.writeUTF(message);
    }
    public String receiveMessage() throws IOException {
        DataInputStream in = new DataInputStream(client.getInputStream());
        if (in.available() == 0){
            return null;
        }
        String message = in.readUTF();
        return message;
    }

}
